package com.example.flywaytask.services.impl;

import com.example.flywaytask.config.EntityName;
import com.example.flywaytask.entities.Subject;
import com.example.flywaytask.repositories.SubjectsRepository;
import com.example.flywaytask.services.SubjectsService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectsServiceImpl implements SubjectsService {
    private final SubjectsRepository subjectsRepository;

    @Override
    public List<Subject> getAll() {
        return subjectsRepository.findAll();
    }

    @Override
    public Subject getById(Integer id) {
        return subjectsRepository.getById(id);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectsRepository.save(subject);
    }

    @Override
    public void delete(Integer id) {
        subjectsRepository.deleteById(id);
    }

    @Override
    public RecordCountResponseDTO count() {
        return new RecordCountResponseDTO(subjectsRepository.count());
    }

    @Override
    public EntityName getEntityName() {
        return EntityName.SUBJECT;
    }
}
