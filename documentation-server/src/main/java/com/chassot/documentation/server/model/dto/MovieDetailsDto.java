package com.chassot.documentation.server.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Get Movies By Title Response", description = "Specification of all movies found by title")
public class MovieDetailsDto {

    @ApiModelProperty(value = "Movie title", dataType = "string", example = "Star Wars")
    private String title;

    @ApiModelProperty(value = "Movie director", dataType = "string", example = "George Lucas")
    private String director;

    public MovieDetailsDto(String title, String director) {
        this.title = title;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }
}
