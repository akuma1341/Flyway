package com.example.flywaytask.controllers;

import com.example.flywaytask.services.ExamMarksService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/examMarks")
@RequiredArgsConstructor
public class ExamMarkRestController {
    private final ExamMarksService examMarksService;

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public RecordCountResponseDTO getExamMarksCount() {
        return examMarksService.count();
    }
}
