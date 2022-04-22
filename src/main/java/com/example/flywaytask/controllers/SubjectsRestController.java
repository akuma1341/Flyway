package com.example.flywaytask.controllers;

import com.example.flywaytask.services.SubjectsService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectsRestController {
    private final SubjectsService subjectsService;

    @GetMapping(value = "/count")
    public RecordCountResponseDTO getSubjectsCount() {
        return subjectsService.count();
    }
}
