package com.example.flywaytask.converters;

import com.example.flywaytask.dto.StudentRequestDto;
import com.example.flywaytask.entities.Student;

public interface StudentConverter {

    Student toEntity(StudentRequestDto dto);

    Student toEntity(Integer id, StudentRequestDto dto);
}
