package com.chassot.movie.management.api.configuration;

import com.chassot.commons.utils.AuthUtils;
import com.chassot.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserAccountRepository userAccountRepository;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Value("${movie-delivery.security.secret}")
    private String secret;

    @Autowired
    public SecurityConfiguration(UserAccountRepository userAccountRepository, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.userAccountRepository = userAccountRepository;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                    .csrf()
                        .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                        .anyRequest()
                            .authenticated()
                .and()
                    .addFilterBefore(new AuthorizationTokenFilter(userAccountRepository, secret), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return AuthUtils.setCorsConfiguration();
    }
}
