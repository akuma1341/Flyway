package com.example.flywaytask.controllers;

import com.example.flywaytask.exceptions.NotFoundException;
import com.example.flywaytask.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleValidationException(ValidationException e) {
        return createErrorResponse(e.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        return createErrorResponse(e.getMessage(), NOT_FOUND);
    }

    private ErrorResponse createErrorResponse(String exceptionMessage, HttpStatus status) {
        return ErrorResponse.builder()
                .message(exceptionMessage)
                .status(status)
                .timestamp(now())
                .build();
    }
}
