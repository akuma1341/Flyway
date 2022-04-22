package com.example.flywaytask.services;

import com.example.flywaytask.repositories.ExamMarksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ExamMarksServiceTests {

    @MockBean
    private ExamMarksRepository examMarksRepository;

    @Autowired
    private ExamMarksService examMarksService;

    @Test
    void examMarksRecordCountIsEqual100() {
        when(examMarksRepository.count()).thenReturn(100L);

        assertEquals(100L, examMarksService.count().getRecordCount());

        verify(examMarksRepository, times(1)).count();
    }
}
