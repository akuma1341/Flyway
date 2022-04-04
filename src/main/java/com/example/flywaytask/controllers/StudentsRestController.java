package com.example.flywaytask.controllers;

import com.example.flywaytask.services.StudentsService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentsRestController {
    private final StudentsService studentsService;

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public RecordCountResponseDTO getStudentsCount() {
        return studentsService.count();
    }
}
