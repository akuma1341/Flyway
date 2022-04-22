package com.example.flywaytask.controllers;

import com.example.flywaytask.config.EntityName;
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
public class RecordCountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenEntityNameStudent_whenGetRecordCount_thenStatus200AndRecordCountEquals20() throws Exception {
        mockMvc.perform(
                get("/api/v1/recordCount/{entityName}/recordCount", EntityName.STUDENT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordCount").value(20));
    }

    @Test
    void givenEntityNameSubject_whenGetRecordCount_thenStatus200AndRecordCountEquals6() throws Exception {
        mockMvc.perform(
                get("/api/v1/recordCount/{entityName}/recordCount", EntityName.SUBJECT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordCount").value(6));
    }

    @Test
    void givenEntityNameMark_whenGetRecordCount_thenStatus200AndRecordCountEquals87() throws Exception {
        mockMvc.perform(
                get("/api/v1/recordCount/{entityName}/recordCount", EntityName.MARK))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordCount").value(87));
    }
}
