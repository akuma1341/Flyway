package com.example.flywaytask.services;

import com.example.flywaytask.config.EntityName;
import com.example.flywaytask.services.dto.RecordCountResponseDTO;

public interface RecordCountGetterService {

    RecordCountResponseDTO count();

    EntityName getEntityName();
}
