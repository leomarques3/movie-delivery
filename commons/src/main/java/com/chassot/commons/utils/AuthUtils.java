package com.chassot.commons.utils;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

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

}
