package com.example.flywaytask.services;

import com.example.flywaytask.entities.Subject;

import java.util.List;

public interface SubjectsService extends RecordCountGetterService {
    List<Subject> getAll();

    Subject getById(Integer id);

    Subject save(Subject subject);

    void delete(Integer id);
}
