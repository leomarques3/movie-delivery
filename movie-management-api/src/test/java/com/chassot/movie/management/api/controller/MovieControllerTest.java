package com.chassot.movie.management.api.controller;

import com.chassot.commons.enumerations.MovieStatusEnum;
import com.chassot.entities.Movie;
import com.chassot.entities.UserAccount;
import com.chassot.repositories.MovieRepository;
import com.chassot.repositories.UserAccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
class MovieControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @BeforeEach
    void initEach() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @DisplayName("Successfully retrieved available movies")
    @Test
    void listMoviesAvailableTest_success() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(get("/movies/available").header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaXNzIjoiYXV0aC1zZXJ2ZXIiLCJhdWQiOiJtb3ZpZS1kZWxpdmVyeSIsImlhdCI6MTU3NjYxNjY5OSwiZXhwIjoxNTg2NzAzMDk5fQ.r6WMOn_IwIh_PTZ0_uTImIK99Etj4YHMH8ZwCik7mpg4GoZp47CDnZAtNL9zzymE0ZkW4xd2nhQctA-nAr3D9w"))
                .andReturn()
                .getResponse();
        String content = mockHttpServletResponse.getContentAsString();
        assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
        assertTrue(content.contains("title"));
    }

    @DisplayName("Failed due bad authentication")
    @Test
    void listMoviesAvailableTest_fail_authentication() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(get("/movies/available").header("Authorization", "Bearer sad"))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.UNAUTHORIZED.value(), mockHttpServletResponse.getStatus());
    }

    @DisplayName("Successfully retrieved available movies")
    @Test
    void searchByTitleTest_success() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(get("/movies/find/{title}", "war").header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaXNzIjoiYXV0aC1zZXJ2ZXIiLCJhdWQiOiJtb3ZpZS1kZWxpdmVyeSIsImlhdCI6MTU3NjYxNjY5OSwiZXhwIjoxNTg2NzAzMDk5fQ.r6WMOn_IwIh_PTZ0_uTImIK99Etj4YHMH8ZwCik7mpg4GoZp47CDnZAtNL9zzymE0ZkW4xd2nhQctA-nAr3D9w"))
                .andReturn()
                .getResponse();
        String content = mockHttpServletResponse.getContentAsString();
        assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
        assertTrue(content.contains("Star"));
    }

    @DisplayName("Successfully retrieved available movies")
    @Test
    void rentTest_success() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(put("/movies/rent/{id}", "4").header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaXNzIjoiYXV0aC1zZXJ2ZXIiLCJhdWQiOiJtb3ZpZS1kZWxpdmVyeSIsImlhdCI6MTU3NjYxNjY5OSwiZXhwIjoxNTg2NzAzMDk5fQ.r6WMOn_IwIh_PTZ0_uTImIK99Etj4YHMH8ZwCik7mpg4GoZp47CDnZAtNL9zzymE0ZkW4xd2nhQctA-nAr3D9w"))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }

    @DisplayName("Successfully retrieved available movies")
    @Test
    void returnMovieTest_success() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(put("/movies/return/{id}", "1").header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaXNzIjoiYXV0aC1zZXJ2ZXIiLCJhdWQiOiJtb3ZpZS1kZWxpdmVyeSIsImlhdCI6MTU3NjYxNjY5OSwiZXhwIjoxNTg2NzAzMDk5fQ.r6WMOn_IwIh_PTZ0_uTImIK99Etj4YHMH8ZwCik7mpg4GoZp47CDnZAtNL9zzymE0ZkW4xd2nhQctA-nAr3D9w"))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }

    @AfterEach
    void cleanUp() {
        Movie movie = movieRepository.findById(4L).get();
        if (movie.getStatus().equals(MovieStatusEnum.RENTED)) {
            movie.setStatus(MovieStatusEnum.AVAILABLE);
            movie.setUserAccount(null);
            movieRepository.save(movie);
        }

        movie = movieRepository.findById(1L).get();
        if (movie.getStatus().equals(MovieStatusEnum.AVAILABLE)) {
            movie.setStatus(MovieStatusEnum.RENTED);
            UserAccount userAccount = userAccountRepository.findById(1L).get();
            movie.setUserAccount(userAccount);
            movieRepository.save(movie);
        }

    }

}
