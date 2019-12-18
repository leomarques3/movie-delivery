package com.chassot.movie.management.api.configuration;

import com.chassot.commons.utils.AuthUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Integer statusCode = HttpStatus.UNAUTHORIZED.value();
        String exceptionResponse = AuthUtils.buildAuthEntryPointResponse(authException.getMessage(), statusCode);
        AuthUtils.printResponse(response, statusCode, exceptionResponse);
    }

}
