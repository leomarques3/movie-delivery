package com.chassot.entities;

import com.chassot.commons.enumerations.MovieStatusEnum;

import javax.persistence.*;

@Entity(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private MovieDirector movieDirector;

    @Enumerated(EnumType.STRING)
    private MovieStatusEnum status;

    @ManyToOne
    private UserAccount userAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieDirector getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(MovieDirector movieDirector) {
        this.movieDirector = movieDirector;
    }

    public MovieStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MovieStatusEnum status) {
        this.status = status;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
