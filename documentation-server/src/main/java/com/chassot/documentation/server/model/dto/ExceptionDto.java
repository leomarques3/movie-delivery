package com.chassot.documentation.server.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel(value = "Error Response", description = "This model will be used whenever an error occurs")
public class ExceptionDto {

    @ApiModelProperty(value = "Timestamp of the moment the error occurred", dataType = "string", example = "2019-10-20T15:27:32.09")
    private LocalDateTime timestamp;

    @ApiModelProperty(value = "HTTP status code of the error", dataType = "integer", example = "401")
    private Integer statusCode;

    @ApiModelProperty(value = "List of errors", dataType = "List", example = "[Username or password is invalid, No movies available]")
    private List<String> errors;

    public ExceptionDto(LocalDateTime timestamp, Integer statusCode, List<String> errors) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public List<String> getErrors() {
        return errors;
    }
}
