package com.chassot.commons.dto;

public class MoviesAvailableDto {

    private String title;
    private String director;
    private Long quantity;

    public MoviesAvailableDto(String title, String director, Long quantity) {
        this.title = title;
        this.director = director;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
