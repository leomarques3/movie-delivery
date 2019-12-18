package com.chassot.documentation.server.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Get Movies Available Response", description = "Specification of all available movies")
public class MoviesAvailableDto {

    @ApiModelProperty(value = "Movie title", dataType = "string", example = "Star Wars")
    private String title;

    @ApiModelProperty(value = "Movie director", dataType = "string", example = "George Lucas")
    private String director;

    @ApiModelProperty(value = "Number of copies available", dataType = "integer", example = "5")
    private Long quantity;

    public MoviesAvailableDto(String title, String director, Long quantity) {
        this.title = title;
        this.director = director;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public Long getQuantity() {
        return quantity;
    }

}
