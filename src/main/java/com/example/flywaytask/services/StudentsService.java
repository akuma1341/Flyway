package com.example.flywaytask.services;

import com.example.flywaytask.entities.Student;

import java.util.List;

public interface StudentsService extends RecordCountGetterService {
    List<Student> getAll();

    Student getById(Integer id);

    Student save(Student student);

    void delete(Integer id);
}
