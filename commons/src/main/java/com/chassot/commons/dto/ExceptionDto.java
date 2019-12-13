package com.chassot.commons.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionDto {

    private LocalDateTime timestamp;
    private Integer statusCode;
    private List<String> errors;

    public ExceptionDto(LocalDateTime timestamp, Integer statusCode, List<String> errors) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
