package com.chassot.documentation.server.controller;

import com.chassot.documentation.server.model.dto.ExceptionDto;
import com.chassot.documentation.server.model.form.SignUpForm;
import io.swagger.annotations.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Api(protocols = "http", tags = "Authentication Server")
public class AuthController {

    @ApiOperation(
            value = "Sign in into the application",
            httpMethod = "POST",
            nickname = "sign in",
            authorizations = {@Authorization(value="basicAuth")},
            responseHeaders = {@ResponseHeader(name = "Authorization", description = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully authenticated user"),
            @ApiResponse(code = 401, message = "Error during authentication", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/login")
    public ResponseEntity<Void> login() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxM");
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Sign up into the application",
            httpMethod = "POST",
            nickname = "sign up")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a new user"),
            @ApiResponse(code = 400, message = "There are problems with the request body", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody SignUpForm signUpForm) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
