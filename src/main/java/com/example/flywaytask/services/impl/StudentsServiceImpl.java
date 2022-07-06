package com.example.flywaytask.services.impl;

import com.example.flywaytask.config.EntityName;
import com.example.flywaytask.entities.Student;
import com.example.flywaytask.exceptions.NotFoundException;
import com.example.flywaytask.exceptions.ValidationException;
import com.example.flywaytask.repositories.StudentsRepository;
import com.example.flywaytask.services.StudentsService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
        if (id == null || id < 0) {
            throw new ValidationException("ID cant be empty or less than 0");
        }
        return studentsRepository.findById(id).orElseThrow(() -> new NotFoundException("Student with ID " + id + " not found"));
    }

    @Override
    public Student save(Student student) {
        if (student == null) {
            throw new ValidationException("Student object is null");
        }
        if (student.getBirthDate() == null) {
            throw new ValidationException("Birthdate cant be empty");
        }
        return studentsRepository.save(student);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Student> saveAll(List<Student> students) {
        if (students.isEmpty()) {
            throw new ValidationException("List is empty");
        }

        boolean incorrectStudentExist = students.stream().
                anyMatch(student -> student.getBirthDate() == null);
        if (incorrectStudentExist) {
            throw new ValidationException("Birthdate cant be empty");
        }
        return studentsRepository.saveAll(students);
    }

    @Override
    public void delete(Integer id) {
        if (id == null || id < 0) {
            throw new ValidationException("ID cant be empty or less than 0");
        }
        if (studentsRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
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
