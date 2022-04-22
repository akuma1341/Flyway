package com.example.flywaytask.services;

import com.example.flywaytask.repositories.SubjectsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SubjectServiceTests {

    @MockBean
    private SubjectsRepository subjectsRepository;

    @Autowired
    private SubjectsService subjectsService;

    @Test
    void subjectsRecordCountIsEqual40() {
        when(subjectsRepository.count()).thenReturn(40L);

        assertEquals(40L, subjectsService.count().getRecordCount());

        verify(subjectsRepository, times(1)).count();
    }
}
