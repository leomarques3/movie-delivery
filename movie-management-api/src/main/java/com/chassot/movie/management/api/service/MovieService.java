package com.chassot.movie.management.api.service;

import com.chassot.commons.dto.MovieDetailsDto;
import com.chassot.commons.dto.MoviesAvailableDto;
import com.chassot.commons.exceptions.NoMoviesAvailableException;
import com.chassot.commons.exceptions.NoMoviesFoundException;

import java.util.List;

public interface MovieService {

    List<MoviesAvailableDto> listMoviesAvailable() throws NoMoviesAvailableException;

    List<MovieDetailsDto> searchByTitle(String title) throws NoMoviesFoundException;

    void rent(Long movieId) throws NoMoviesAvailableException;

    void returnMovie(Long id);
}
