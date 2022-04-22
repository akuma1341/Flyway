package com.example.flywaytask.services.impl;

import com.example.flywaytask.repositories.StudentsRepository;
import com.example.flywaytask.services.GenericRecordCountGetterService;
import com.example.flywaytask.services.dto.StudentsRecordCountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenericStudentsRecordCountGetterServiceImpl implements GenericRecordCountGetterService<StudentsRecordCountDTO> {
    private final StudentsRepository studentsRepository;

    @Override
    public StudentsRecordCountDTO getCount() {
        return new StudentsRecordCountDTO(studentsRepository.count());
    }
}
