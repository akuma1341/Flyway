package com.example.flywaytask.repositories;

import com.example.flywaytask.entities.ExamMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamMarksRepository extends JpaRepository<ExamMark, Integer> {
}
