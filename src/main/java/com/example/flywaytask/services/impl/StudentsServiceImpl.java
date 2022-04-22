package com.example.flywaytask.services.impl;

import com.example.flywaytask.config.EntityName;
import com.example.flywaytask.entities.Student;
import com.example.flywaytask.repositories.StudentsRepository;
import com.example.flywaytask.services.StudentsService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentsServiceImpl implements StudentsService {
    private final StudentsRepository studentsRepository;

    @Override
    public List<Student> getAll() {
        return studentsRepository.findAll();
    }

    @Override
    public Student getById(Integer id) {
        return studentsRepository.getById(id);
    }

    @Override
    public Student save(Student student) {
        return studentsRepository.save(student);
    }

    @Override
    public void delete(Integer id) {
        studentsRepository.deleteById(id);
    }

    @Override
    public RecordCountResponseDTO count() {
        return new RecordCountResponseDTO(studentsRepository.count());
    }

    @Override
    public EntityName getEntityName() {
        return EntityName.STUDENT;
    }
}
