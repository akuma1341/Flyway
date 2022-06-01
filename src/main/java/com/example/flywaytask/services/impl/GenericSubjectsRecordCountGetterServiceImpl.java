package com.example.flywaytask.services.impl;

import com.example.flywaytask.repositories.SubjectsRepository;
import com.example.flywaytask.services.GenericRecordCountGetterService;
import com.example.flywaytask.services.dto.SubjectsRecordCountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenericSubjectsRecordCountGetterServiceImpl implements GenericRecordCountGetterService<SubjectsRecordCountDTO> {

    private final SubjectsRepository subjectsRepository;

    @Override
    public SubjectsRecordCountDTO getCount() {
        return new SubjectsRecordCountDTO(subjectsRepository.count());
    }
}
