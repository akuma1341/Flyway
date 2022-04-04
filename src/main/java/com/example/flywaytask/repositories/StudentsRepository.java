package com.example.flywaytask.repositories;

import com.example.flywaytask.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Integer> {
}
