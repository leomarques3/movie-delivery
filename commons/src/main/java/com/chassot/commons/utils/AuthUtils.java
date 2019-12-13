package com.chassot.commons.utils;

import com.chassot.commons.constants.CommonConstants;
import com.chassot.commons.dto.ExceptionDto;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class AuthUtils {

    public static CorsConfigurationSource setCorsConfiguration() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "TRACE", "CONNECT"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(86400000L);
        corsConfiguration.setAllowedHeaders(Arrays.asList("Accept", "Access-Control-Request-Headers", "Access-Control-Request-Method", "Authorization", "Cache-Control", "Content-Type", "Origin", "X-Requested-With"));
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }

    public static String buildAuthEntryPointResponse(String errorMessage, Integer statusCode) {
        Clock clock = Clock.system(ZoneId.of(CommonConstants.TIME_ZONE_BRAZIL));
        LocalDateTime timestamp = LocalDateTime.now(clock);
        List<String> errors = Arrays.asList(errorMessage);

        ExceptionDto exceptionDto = new ExceptionDto(timestamp, statusCode, errors);
        Gson gson = DateUtils.getSerializedLocalDateTimeBuilder();
        return gson.toJson(exceptionDto);
    }

    public static void printResponse(HttpServletResponse response, Integer statusCode, String exceptionResponse) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(statusCode);
        out.print(exceptionResponse);
        out.flush();
    }

}
