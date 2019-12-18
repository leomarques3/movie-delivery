package com.chassot.movie.management.api.controller;

import com.chassot.commons.dto.MovieDetailsDto;
import com.chassot.commons.dto.MoviesAvailableDto;
import com.chassot.commons.exceptions.NoMoviesAvailableException;
import com.chassot.commons.exceptions.NoMoviesFoundException;
import com.chassot.movie.management.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/available")
    public ResponseEntity listMoviesAvailable() throws NoMoviesAvailableException {
        List<MoviesAvailableDto> moviesAvailable = movieService.listMoviesAvailable();
        return new ResponseEntity<>(moviesAvailable, HttpStatus.OK);
    }

    @GetMapping("/find/{title}")
    public ResponseEntity searchByTitle(@PathVariable("title") String title) throws NoMoviesFoundException {
        List<MovieDetailsDto> movieDetailsDtos = movieService.searchByTitle(title);
        return new ResponseEntity<>(movieDetailsDtos, HttpStatus.OK);
    }

    @PutMapping("/rent/{id}")
    public ResponseEntity rent(@PathVariable("id") Long id) throws NoMoviesAvailableException {
        movieService.rent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/return/{id}")
    public ResponseEntity returnMovie(@PathVariable("id") Long id) {
        movieService.returnMovie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
