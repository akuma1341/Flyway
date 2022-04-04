package com.example.flywaytask.services;

import com.example.flywaytask.entities.Student;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;

import java.util.List;

public interface StudentsService {
    List<Student> getAll();

    Student getById(Integer id);

    Student save(Student student);

    void delete(Integer id);

    RecordCountResponseDTO count();
}
