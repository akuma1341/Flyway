package com.example.flywaytask.controllers;

import com.example.flywaytask.dto.StudentRequestDto;
import com.example.flywaytask.entities.Student;
import com.example.flywaytask.repositories.StudentsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class StudentsRestControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void whenGetAllStudents_thenStatusOk() throws Exception {
        mockMvc.perform(
                get("/api/v1/students"))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}"));
    }

    @Test
    void givenStudent_whenGetStudentById_thenStatusOk() throws Exception {
        Student student = studentsRepository.getById(1);
        mockMvc.perform(
                get("/api/v1/students/{id}", student.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        responseFields(
                                fieldWithPath("id").description("Students id"),
                                fieldWithPath("firstName").description("Students first name"),
                                fieldWithPath("lastName").description("Students last name"),
                                fieldWithPath("birthDate").description("Students birth date")
                        )));
    }

    @Test
    void givenStudent_whenPostStudent_thenStatusOk() throws Exception {
        StudentRequestDto student = new StudentRequestDto(
                null, "First", "First", Date.valueOf("1999-12-31"));

        mockMvc.perform(
                post("/api/v1/students")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}", requestBody(), responseBody()));
    }

    @Test
    void givenStudent_whenPutStudent_thenStatusOk() throws Exception {
        StudentRequestDto student = new StudentRequestDto(
                1, "First", "First", Date.valueOf("1999-12-31"));

        mockMvc.perform(
                put("/api/v1/students/{id}", student.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}", requestBody(), responseBody()));
    }
}
