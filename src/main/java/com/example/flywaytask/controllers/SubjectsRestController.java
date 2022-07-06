package com.example.flywaytask.controllers;

import com.example.flywaytask.entities.Subject;
import com.example.flywaytask.repositories.SubjectsRepository;
import com.example.flywaytask.services.SubjectsService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectsRestController {

    private final SubjectsService subjectsService;
    private final SubjectsRepository repository;

    @GetMapping(value = "/count")
    public RecordCountResponseDTO getSubjectsCount() {
        return subjectsService.count();
    }

    @PutMapping("/{id}/{sName}")
    public Subject update(@PathVariable(name = "id") int id, @PathVariable(name = "sName") String subjectName) {
        Subject subject = repository.findById(id).get();
        subject.setName(subjectName);
        return repository.save(subject);
    }

    @PostMapping(value = "/{sName}")
    public Subject create(@PathVariable(name = "sName") String subjectName) {
        Subject subject = new Subject();
        subject.setName(subjectName);
        return repository.save(subject);
    }
}
