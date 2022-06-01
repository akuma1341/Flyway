package com.example.flywaytask.controllers;

import com.example.flywaytask.entities.Student;
import com.example.flywaytask.services.EntityCRUDService;
import com.example.flywaytask.services.StudentsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class SomeTests {

    @Autowired
    private StudentsService studentsService;

    @Test
    @Transactional
    void givenStudent_whenCompare_thenCorrect() {
        Student student = studentsService.getById(10);
        assertThat(student.getFirstName(), equalTo("Thomas"));
        assertThat(student.getLastName(), allOf(containsString("Dav"), endsWith("ies")));
        assertThat(student.getBirthDate(), notNullValue());
    }

    @Test
    @Transactional
    void givenStudentList_whenCompare_thenCorrect() {
        List<Student> students = studentsService.getAll();
        assertThat(students, is(not(empty())));
        assertThat(students, hasSize(20));
    }

    @Test
    void givenStudentsService_whenTypeCompatible_thenTrue() {
        assertThat(studentsService.getClass(), typeCompatibleWith(EntityCRUDService.class));
    }
}
