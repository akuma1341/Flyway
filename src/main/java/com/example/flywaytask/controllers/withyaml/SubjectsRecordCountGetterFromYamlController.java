package com.example.flywaytask.controllers.withyaml;

import com.example.flywaytask.services.RecordCountGetterService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subjectsRecordCountGetterService")
@RequiredArgsConstructor
public class SubjectsRecordCountGetterFromYamlController {

    private final RecordCountGetterService subjectsRecordCountService;

    @GetMapping
    public RecordCountResponseDTO getCount() {
        return subjectsRecordCountService.count();
    }
}
