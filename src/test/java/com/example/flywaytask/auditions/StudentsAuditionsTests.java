package com.example.flywaytask.auditions;

import com.example.flywaytask.entities.Student;
import com.example.flywaytask.services.StudentsService;
import com.example.flywaytask.services.audit.StudentsAuditService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StudentsAuditionsTests {

    @Autowired
    private StudentsAuditService studentsAuditService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StudentsService studentsService;

    @Test
    void given2Students_whenSave2Students_thenStudentsAuditionCreates2Records() {
        Student student1 = Student.builder()
                .firstName("First")
                .lastName("First")
                .birthDate(Date.valueOf("2000-01-01"))
                .build();
        Student student2 = Student.builder()
                .firstName("Second")
                .lastName("Second")
                .birthDate(Date.valueOf("2000-12-31"))
                .build();

        List<Student> studentsToCreate = List.of(student1, student2);

        studentsService.saveAll(studentsToCreate);

        List<?> studentsRevisions = entityManager.createNativeQuery("SELECT * FROM students_audition")
                .getResultList();

        assertEquals(2, studentsRevisions.size());
    }

    @Test
    void givenStudent_whenSaveStudent_thenRevisionCreatesAndDataIsCorrect() throws IOException {
        Student student = Student.builder()
                .firstName("First")
                .lastName("First")
                .birthDate(Date.valueOf("2000-01-01"))
                .build();
        Student createdStudent = studentsService.save(student);

        createdStudent.setFirstName("Changed");
        createdStudent.setLastName("Changed");

        studentsService.save(createdStudent);

        List<?> studentsRevisions = studentsAuditService.getRevisions(createdStudent.getId(), true);
        String firstRevisionAsJson = objectMapper.writeValueAsString(studentsRevisions.get(0));
        String secondRevisionAsJson = objectMapper.writeValueAsString(studentsRevisions.get(1));

        JsonNode firstRevisionJsonNodeRoot = objectMapper.readTree(firstRevisionAsJson);
        JsonNode firstRevisionStudentInfoNode = firstRevisionJsonNodeRoot.get(0);
        JsonNode firstRevisionModifyTypeNode = firstRevisionJsonNodeRoot.get(2);

        JsonNode secondRevisionJsonNodeRoot = objectMapper.readTree(secondRevisionAsJson);
        JsonNode secondRevisionStudentInfoNode = secondRevisionJsonNodeRoot.get(0);
        JsonNode secondRevisionModifyTypeNode = secondRevisionJsonNodeRoot.get(2);

        assertEquals(21, firstRevisionStudentInfoNode.get("id").asInt());
        assertEquals("First", firstRevisionStudentInfoNode.get("firstName").asText());
        assertEquals("First", firstRevisionStudentInfoNode.get("lastName").asText());
        assertEquals("2000-01-01", firstRevisionStudentInfoNode.get("birthDate").asText());
        assertEquals("ADD", firstRevisionModifyTypeNode.asText());

        assertEquals(21, secondRevisionStudentInfoNode.get("id").asInt());
        assertEquals("Changed", secondRevisionStudentInfoNode.get("firstName").asText());
        assertEquals("Changed", secondRevisionStudentInfoNode.get("lastName").asText());
        assertEquals("2000-01-01", secondRevisionStudentInfoNode.get("birthDate").asText());
        assertEquals("MOD", secondRevisionModifyTypeNode.asText());
    }
}
