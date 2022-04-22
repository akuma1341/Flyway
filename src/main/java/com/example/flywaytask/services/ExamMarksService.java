package com.example.flywaytask.services;

import com.example.flywaytask.entities.ExamMark;

import java.util.List;

public interface ExamMarksService extends RecordCountGetterService {
    List<ExamMark> getAll();

    ExamMark getById(Integer id);

    ExamMark save(ExamMark examMark);

    void delete(Integer id);
}
