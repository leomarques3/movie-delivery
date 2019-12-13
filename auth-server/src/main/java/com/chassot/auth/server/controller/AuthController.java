package com.chassot.auth.server.controller;

import com.chassot.auth.server.service.TokenService;
import com.chassot.commons.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenService jwtTokenServiceImpl;

    @Autowired
    public AuthController(TokenService jwtTokenServiceImpl) {
        this.jwtTokenServiceImpl = jwtTokenServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = jwtTokenServiceImpl.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", SecurityConstants.JWT_TOKEN_PREFIX + " " + token);
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

}
