package com.example.flywaytask.controllers.withyaml;

import com.example.flywaytask.services.RecordCountGetterService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/studentsRecordCountGetterService")
@Hidden
@Tag(name = "Students count", description = "By properties from yaml")
@RequiredArgsConstructor
public class StudentsRecordCountGetterFromYamlController {

    private final RecordCountGetterService studentsRecordCountService;

    @GetMapping
    public RecordCountResponseDTO getCount() {
        return studentsRecordCountService.count();
    }
}
