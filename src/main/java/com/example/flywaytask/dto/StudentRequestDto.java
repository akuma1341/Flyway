package com.example.flywaytask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Student DTO")
public class StudentRequestDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY,
            description = "id", example = "23")
    private Integer id;

    @Schema(description = "Students first name", example = "John")
    private String firstName;

    @Schema(description = "Students last name", example = "Smith")
    private String lastName;

    @Schema(description = "Students birth date", example = "1999-12-31")
    private Date birthDate;
}
