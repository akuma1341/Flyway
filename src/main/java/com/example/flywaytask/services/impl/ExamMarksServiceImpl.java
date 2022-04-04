package com.example.flywaytask.services.impl;

import com.example.flywaytask.entities.ExamMark;
import com.example.flywaytask.repositories.ExamMarksRepository;
import com.example.flywaytask.services.ExamMarksService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamMarksServiceImpl implements ExamMarksService {
    private final ExamMarksRepository examMarksRepository;

    @Override
    public List<ExamMark> getAll() {
        return examMarksRepository.findAll();
    }

    @Override
    public ExamMark getById(Integer id) {
        return examMarksRepository.getById(id);
    }

    @Override
    public ExamMark save(ExamMark examMark) {
        return examMarksRepository.save(examMark);
    }

    @Override
    public void delete(Integer id) {
        examMarksRepository.deleteById(id);
    }

    @Override
    public RecordCountResponseDTO count() {
        return new RecordCountResponseDTO(examMarksRepository.count());
    }
}
