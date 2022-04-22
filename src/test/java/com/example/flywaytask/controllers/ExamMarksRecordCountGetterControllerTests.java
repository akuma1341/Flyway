package com.example.flywaytask.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExamMarksRecordCountGetterControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGetExamMarksRecordCount_thenStatus200AndRecordCountEquals87() throws Exception {
        mockMvc.perform(
                get("/api/v1/examMarksRecordCount"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordCount").value(87L));
    }
}
