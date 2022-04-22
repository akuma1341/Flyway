package com.example.flywaytask.controllers.withgeneric;

import com.example.flywaytask.services.GenericRecordCountGetterService;
import com.example.flywaytask.services.dto.SubjectsRecordCountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subjectsRecordCount")
@RequiredArgsConstructor
public class SubjectsRecordCountGetterController {
    private final GenericRecordCountGetterService<SubjectsRecordCountDTO> recordCountGetter;

    @GetMapping
    public SubjectsRecordCountDTO count() {
        return recordCountGetter.getCount();
    }
}
