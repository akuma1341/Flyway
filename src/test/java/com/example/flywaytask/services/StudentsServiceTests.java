package com.example.flywaytask.services;

import com.example.flywaytask.repositories.StudentsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentsServiceTests {

    @MockBean
    private StudentsRepository studentsRepository;

    @Autowired
    private StudentsService studentsService;

    @Test
    void studentsRecordCountIsEqual20() {
        when(studentsRepository.count()).thenReturn(20L);

        assertEquals(20L, studentsService.count().getRecordCount());

        verify(studentsRepository, times(1)).count();
    }
}
