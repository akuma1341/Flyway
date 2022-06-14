package com.example.flywaytask.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Getter
@RequiredArgsConstructor
public class StudentRequestDto {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;
}
