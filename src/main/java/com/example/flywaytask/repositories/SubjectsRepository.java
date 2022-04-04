package com.example.flywaytask.repositories;

import com.example.flywaytask.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectsRepository extends JpaRepository<Subject, Integer> {
}
