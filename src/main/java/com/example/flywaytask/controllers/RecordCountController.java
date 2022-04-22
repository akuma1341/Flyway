package com.example.flywaytask.controllers;

import com.example.flywaytask.config.EntityName;
import com.example.flywaytask.services.RecordCountGetterService;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/recordCount")
@RequiredArgsConstructor
public class RecordCountController {

    private final Map<EntityName, RecordCountGetterService> recordCountGetters;

    @GetMapping(value = "/{entityName}/recordCount")
    public RecordCountResponseDTO getRecordCount(@PathVariable(value = "entityName") EntityName entityName) {
        return recordCountGetters.get(entityName).count();
    }

    @GetMapping("/all")
    public RecordCountResponseDTO getAllRecordCount() {
        long recordCount = recordCountGetters.values().stream()
                .mapToLong(recordCountGetter -> recordCountGetter.count().getRecordCount())
                .sum();
        return new RecordCountResponseDTO(recordCount);
    }
}
