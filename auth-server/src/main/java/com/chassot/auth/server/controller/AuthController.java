package com.chassot.auth.server.controller;

import com.chassot.auth.server.model.form.SignUpForm;
import com.chassot.auth.server.service.SignUpService;
import com.chassot.auth.server.service.TokenService;
import com.chassot.auth.server.service.impl.SignUpValidatorImpl;
import com.chassot.commons.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final SignUpValidatorImpl signUpValidatorImpl;
    private final SignUpService userSignUpServiceImpl;
    private final TokenService jwtTokenServiceImpl;

    @Autowired
    public AuthController(SignUpValidatorImpl signUpValidatorImpl, SignUpService userSignUpServiceImpl, TokenService jwtTokenServiceImpl) {
        this.signUpValidatorImpl = signUpValidatorImpl;
        this.userSignUpServiceImpl = userSignUpServiceImpl;
        this.jwtTokenServiceImpl = jwtTokenServiceImpl;
    }

    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody SignUpForm signUpForm, BindingResult bindingResult) throws MethodArgumentNotValidException {
        signUpValidatorImpl.validate(signUpForm, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        userSignUpServiceImpl.save(signUpForm);

        return new ResponseEntity<>(HttpStatus.CREATED);
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
