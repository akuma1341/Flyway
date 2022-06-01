package com.example.flywaytask.controllers.withgeneric;

import com.example.flywaytask.services.GenericRecordCountGetterService;
import com.example.flywaytask.services.dto.ExamMarksRecordCountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/examMarksRecordCount")
@RequiredArgsConstructor
public class ExamMarksRecordCountGetterController {

    private final GenericRecordCountGetterService<ExamMarksRecordCountDTO> recordCountGetter;

    @GetMapping
    public ExamMarksRecordCountDTO count() {
        return recordCountGetter.getCount();
    }
}
