package com.chassot.movie.management.api.controller;

import com.chassot.commons.dto.ExceptionDto;
import com.chassot.commons.exceptions.NoMoviesAvailableException;
import com.chassot.commons.exceptions.NoMoviesFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoMoviesAvailableException.class)
    public ResponseEntity<Object> handleNoMoviesAvailable(NoMoviesAvailableException ex, WebRequest request) {
        LocalDateTime timestamp = LocalDateTime.now();
        Integer statusCode = HttpStatus.NO_CONTENT.value();
        List<String> errors = Arrays.asList(ex.getMessage());
        return ResponseEntity.status(statusCode).body(new ExceptionDto(timestamp, statusCode, errors));
    }

    @ExceptionHandler(NoMoviesFoundException.class)
    public ResponseEntity<Object> handleNoMoviesFound(NoMoviesFoundException ex, WebRequest request) {
        LocalDateTime timestamp = LocalDateTime.now();
        Integer statusCode = HttpStatus.NO_CONTENT.value();
        List<String> errors = Arrays.asList(ex.getMessage());
        return ResponseEntity.status(statusCode).body(new ExceptionDto(timestamp, statusCode, errors));
    }

}
