package com.example.flywaytask.controllers;

import com.example.flywaytask.services.StudentsService;
import com.example.flywaytask.services.audit.StudentsAuditService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentsRestController {

    private final StudentsService studentsService;
    private final StudentsAuditService studentsAuditService;

    @GetMapping(value = "/count")
    public RecordCountResponseDTO getStudentsCount() {
        return studentsService.count();
    }

    @GetMapping(value = "/{id}/revisions")
    public List<?> getRevisions(@PathVariable(name = "id") int id,
                                @RequestParam(value = "fetchChanges", required = false) boolean fetchChanges) {
        return studentsAuditService.getRevisions(id, fetchChanges);
    }
}
