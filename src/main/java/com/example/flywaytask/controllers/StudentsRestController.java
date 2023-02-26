package com.example.flywaytask.controllers;

import com.example.flywaytask.converters.StudentConverter;
import com.example.flywaytask.dto.StudentRequestDto;
import com.example.flywaytask.entities.Student;
import com.example.flywaytask.services.StudentsService;
import com.example.flywaytask.services.audit.StudentsAuditService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "Students", description = "Students main controller")
@RequiredArgsConstructor
public class StudentsRestController {

    private final StudentsService studentsService;
    private final StudentsAuditService studentsAuditService;
    private final StudentConverter studentConverter;

    @GetMapping(value = "")
    @Operation(summary = "Students info", description = "Shows all students info")
    public List<Student> getAll() {
        return studentsService.getAll();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Student info", description = "Shows students info by id")
    public Student getById(@PathVariable(name = "id") @Parameter(description = "Students id") int id) {
        return studentsService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create student", description = "Allows to create new student")
    public Student create(@RequestBody StudentRequestDto studentRequestDto) {
        return studentsService.save(studentConverter.toEntity(studentRequestDto));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update student", description = "Allows to update students info")
    public Student update(@PathVariable(name = "id") @Parameter(description = "Students id") int id,
                          @RequestBody StudentRequestDto studentRequestDto) {
        return studentsService.save(studentConverter.toEntity(id, studentRequestDto));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete student", description = "Allows to delete student by id")
    public void delete(@PathVariable(name = "id") int id) {
        studentsService.delete(id);
    }

    @GetMapping(value = "/count")
    @Operation(summary = "Students count", description = "Shows all students count")
    public RecordCountResponseDTO getStudentsCount() {
        return studentsService.count();
    }

    @GetMapping(value = "/{id}/revisions")
    public List<?> getRevisions(@PathVariable(name = "id") int id,
                                @RequestParam(value = "fetchChanges", required = false) boolean fetchChanges) {
        return studentsAuditService.getRevisions(id, fetchChanges);
    }
}
