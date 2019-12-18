package com.chassot.documentation.server.controller;

import com.chassot.documentation.server.model.dto.ExceptionDto;
import com.chassot.documentation.server.model.dto.MovieDetailsDto;
import com.chassot.documentation.server.model.dto.MoviesAvailableDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
@Api(protocols = "http", tags = "Movie Management API")
public class MovieController {

    @ApiOperation(
            value = "Retrieve all available movies",
            httpMethod = "GET",
            nickname = "available",
            produces = MediaType.APPLICATION_JSON_VALUE,
            authorizations = {@Authorization(value = "Bearer")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved movie list", responseContainer = "list", response = MoviesAvailableDto.class),
            @ApiResponse(code = 204, message = "Did not find any movie available", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/available")
    public ResponseEntity<List<MoviesAvailableDto>> listMoviesAvailable() {
        List<MoviesAvailableDto> moviesAvailable = new ArrayList<>();
        MoviesAvailableDto moviesAvailableDto1 = new MoviesAvailableDto("Star Wars", "George Lucas", 5L);
        MoviesAvailableDto moviesAvailableDto2 = new MoviesAvailableDto("Lord of the Rings", "Peter Jackson", 2L);
        moviesAvailable.add(moviesAvailableDto1);
        moviesAvailable.add(moviesAvailableDto2);
        return new ResponseEntity<>(moviesAvailable, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Search a movie by the title",
            httpMethod = "GET",
            nickname = "find",
            produces = MediaType.APPLICATION_JSON_VALUE,
            authorizations = {@Authorization(value = "Bearer")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found a movie", responseContainer = "list", response = MovieDetailsDto.class),
            @ApiResponse(code = 204, message = "Did not find any movie with the given title", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/find/{title}")
    public ResponseEntity<List<MovieDetailsDto>> searchByTitle(@ApiParam(name = "title", value = "Part of the movie title", required = true, type = "string", example = "rings")
                                            @PathVariable("title") String title) {
        List<MovieDetailsDto> movieDetailsDtos = new ArrayList<>();
        MovieDetailsDto movieDetailsDto = new MovieDetailsDto("Lord of the Rings", "Peter Jackson");
        movieDetailsDtos.add(movieDetailsDto);
        return new ResponseEntity<>(movieDetailsDtos, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Rent a movie by id",
            httpMethod = "PUT",
            nickname = "rent",
            authorizations = {@Authorization(value = "Bearer")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully rented the movie"),
            @ApiResponse(code = 204, message = "Did not find a movie with the given id to be rent", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/rent/{id}")
    public ResponseEntity<Void> rent(@ApiParam(name = "id", value = "Movie id", required = true, type = "integer", example = "2")
                                        @PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return a rented movie by id",
            httpMethod = "PUT",
            nickname = "return",
            authorizations = {@Authorization(value = "Bearer")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned the movie"),
            @ApiResponse(code = 204, message = "Did not find a movie with the given id to be returned", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/return/{id}")
    public ResponseEntity<Void> returnMovie(@ApiParam(name = "id", value = "Movie id", required = true, type = "integer", example = "2")
                               @PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
