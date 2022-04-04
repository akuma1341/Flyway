package com.example.flywaytask.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FlywayTaskControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void controllerGettingStudentsRecordCountReturnStatusOKAndRecordCount20() throws Exception {
        mockMvc.perform(
                get("/api/v1/students/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordCount").value(20));
    }

    @Test
    public void controllerGettingSubjectsRecordCountReturnStatusOKAndRecordCount6() throws Exception {
        mockMvc.perform(
                get("/api/v1/subjects/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordCount").value(6));
    }

    @Test
    public void controllerGettingExamMarksRecordCountReturnStatusOKAndRecordCount87() throws Exception {
        mockMvc.perform(
                get("/api/v1/examMarks/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordCount").value(87));
    }
}
