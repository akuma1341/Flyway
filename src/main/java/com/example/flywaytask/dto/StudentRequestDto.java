package com.example.flywaytask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Getter
@RequiredArgsConstructor
@Schema(description = "Student DTO")
public class StudentRequestDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY,
            description = "id", example = "23")
    private final Integer id;

    @Schema(description = "Students first name", example = "John")
    private final String firstName;

    @Schema(description = "Students last name", example = "Smith")
    private final String lastName;

    @Schema(description = "Students birth date", example = "1999-12-31")
    private final Date birthDate;
}
