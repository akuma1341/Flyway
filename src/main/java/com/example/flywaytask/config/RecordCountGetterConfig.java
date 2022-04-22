package com.example.flywaytask.config;

import com.example.flywaytask.services.RecordCountGetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class RecordCountGetterConfig {

    private final List<RecordCountGetterService> recordCountGetterServiceList;

    @Bean("recordCountGetters")
    public Map<EntityName, RecordCountGetterService> recordCountGetters() {
        return recordCountGetterServiceList.stream()
                .collect(Collectors.toMap(RecordCountGetterService::getEntityName, Function.identity()));
    }

    @Bean
    @ConfigurationProperties(prefix = "my")
    public EntityNameProperties entityNameProperties() {
        return new EntityNameProperties();
    }

    @Bean
    public RecordCountGetterService studentsRecordCountService() {
        return recordCountGetters().get(entityNameProperties().getEntityName());
    }

    @Bean
    public RecordCountGetterService subjectsRecordCountService(@Value("${my.entity.names.subject}") EntityName entityName) {
        return recordCountGetters().get(entityName);
    }
}
