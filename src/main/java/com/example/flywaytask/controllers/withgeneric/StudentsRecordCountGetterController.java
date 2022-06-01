package com.example.flywaytask.controllers.withgeneric;

import com.example.flywaytask.services.GenericRecordCountGetterService;
import com.example.flywaytask.services.dto.StudentsRecordCountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/studentsRecordCount")
@RequiredArgsConstructor
public class StudentsRecordCountGetterController {

    private final GenericRecordCountGetterService<StudentsRecordCountDTO> recordCountGetter;

    @GetMapping
    public StudentsRecordCountDTO count() {
        return recordCountGetter.getCount();
    }
}
