package com.example.flywaytask.services;

import com.example.flywaytask.entities.Subject;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;

import java.util.List;

public interface SubjectsService {
    List<Subject> getAll();

    Subject getById(Integer id);

    Subject save(Subject subject);

    void delete(Integer id);

    RecordCountResponseDTO count();
}
