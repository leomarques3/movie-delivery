package com.chassot.movie.management.api.service.impl;

import com.chassot.commons.dto.MovieDetailsDto;
import com.chassot.commons.dto.MoviesAvailableDto;
import com.chassot.commons.enumerations.ErrorMessageEnum;
import com.chassot.commons.enumerations.MovieStatusEnum;
import com.chassot.commons.exceptions.NoMoviesAvailableException;
import com.chassot.commons.exceptions.NoMoviesFoundException;
import com.chassot.entities.Movie;
import com.chassot.entities.UserAccount;
import com.chassot.movie.management.api.service.MovieService;
import com.chassot.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MoviesAvailableDto> listMoviesAvailable() throws NoMoviesAvailableException {
        List<MoviesAvailableDto> moviesAvailable = movieRepository.findByStatus(MovieStatusEnum.AVAILABLE);
        if (moviesAvailable != null && !moviesAvailable.isEmpty()) {
            return moviesAvailable;
        }

        throw new NoMoviesAvailableException(ErrorMessageEnum.NO_MOVIES_AVAILABLE.getMessage());
    }

    @Override
    public List<MovieDetailsDto> searchByTitle(String title) throws NoMoviesFoundException {
        List<Movie> movies = movieRepository.findByTitleContaining(title);
        if (movies != null && !movies.isEmpty()) {
            return convertFromEntity(movies);
        }

        throw new NoMoviesFoundException(ErrorMessageEnum.NO_MOVIES_FOUND.getMessage());
    }

    @Override
    public void rent(Long id) throws NoMoviesAvailableException {
        Movie movie = movieRepository.findByIdAndStatus(id, MovieStatusEnum.AVAILABLE);

        if (movie == null) {
            throw new NoMoviesAvailableException(ErrorMessageEnum.NO_MOVIES_AVAILABLE.getMessage());
        }

        UserAccount userAccount = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        movie.setUserAccount(userAccount);
        movie.setStatus(MovieStatusEnum.RENTED);
        movieRepository.save(movie);
    }

    @Override
    public void returnMovie(Long id) {
        Movie movie = movieRepository.findById(id).get();
        movie.setUserAccount(null);
        movie.setStatus(MovieStatusEnum.AVAILABLE);
        movieRepository.save(movie);
    }

    private List<MovieDetailsDto> convertFromEntity(List<Movie> movies) {
        List<MovieDetailsDto> movieDetailsDtos = new ArrayList<>();
        for (Movie movie : movies) {
            MovieDetailsDto movieDetailsDto = new MovieDetailsDto(movie.getTitle(), movie.getMovieDirector().getName());
            movieDetailsDtos.add(movieDetailsDto);
        }
        return movieDetailsDtos;
    }

}
