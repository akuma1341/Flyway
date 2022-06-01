package com.example.flywaytask.services.impl;

import com.example.flywaytask.repositories.ExamMarksRepository;
import com.example.flywaytask.services.GenericRecordCountGetterService;
import com.example.flywaytask.services.dto.ExamMarksRecordCountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenericExamMarksRecordCountGetterServiceImpl implements GenericRecordCountGetterService<ExamMarksRecordCountDTO> {

    private final ExamMarksRepository examMarksRepository;

    @Override
    public ExamMarksRecordCountDTO getCount() {
        return new ExamMarksRecordCountDTO(examMarksRepository.count());
    }
}
