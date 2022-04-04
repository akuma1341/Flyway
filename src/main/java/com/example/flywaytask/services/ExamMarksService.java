package com.example.flywaytask.services;

import com.example.flywaytask.entities.ExamMark;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;

import java.util.List;

public interface ExamMarksService {
    List<ExamMark> getAll();

    ExamMark getById(Integer id);

    ExamMark save(ExamMark examMark);

    void delete(Integer id);

    RecordCountResponseDTO count();
}
