package com.example.flywaytask.converters.impl;

import com.example.flywaytask.converters.StudentConverter;
import com.example.flywaytask.dto.StudentRequestDto;
import com.example.flywaytask.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConverterImpl implements StudentConverter {

    @Override
    public Student toEntity(StudentRequestDto dto) {
        return convertToEntity(dto.getId(), dto);
    }

    @Override
    public Student toEntity(Integer id, StudentRequestDto dto) {
        return convertToEntity(id, dto);
    }

    private Student convertToEntity(Integer id, StudentRequestDto dto) {
        return Student.builder()
                .id(id)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthDate(dto.getBirthDate())
                .build();
    }
}
