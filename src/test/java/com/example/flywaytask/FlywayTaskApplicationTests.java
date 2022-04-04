package com.example.flywaytask;

import com.example.flywaytask.entities.Student;
import com.example.flywaytask.services.ExamMarksService;
import com.example.flywaytask.services.StudentsService;
import com.example.flywaytask.services.SubjectsService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlywayTaskApplicationTests {
    @Autowired
    private StudentsService studentsService;
    @Autowired
    private SubjectsService subjectsService;
    @Autowired
    private ExamMarksService examMarkService;

    @Test
    public void studentsRepositoryHave20Records() {
        RecordCountResponseDTO responseDTO = studentsService.count();

        assertEquals(20, responseDTO.getRecordCount());
    }

    @Test
    public void studentsRepositoryHave21RecordsAfterInsertingRecord() {
        Student student = new Student();
        student.setFirstName("FirstName");
        student.setLastName("LastName");
        student.setBirthDate(Date.valueOf("2000-12-31"));

        Student savedStudent = studentsService.save(student);

        RecordCountResponseDTO responseDTO = studentsService.count();

        assertNotNull(savedStudent);
        assertEquals(21, responseDTO.getRecordCount());
    }

    @Test
    public void subjectRepositoryHave6Records() {
        RecordCountResponseDTO responseDTO = subjectsService.count();

        assertEquals(6, responseDTO.getRecordCount());
    }

    @Test
    public void examMarksRepositoryHave87Records() {
        RecordCountResponseDTO responseDTO = examMarkService.count();

        assertEquals(87, responseDTO.getRecordCount());
    }

}
