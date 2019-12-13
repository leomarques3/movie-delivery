package com.chassot.auth.server.controller;

import com.chassot.auth.server.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
class AuthControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void initEach() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @DisplayName("Successfully authenticated user with basic authentication")
    @Test
    void loginTest_success() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(post("/auth/login").header("Authorization", "Basic bGVvY2hhc3NvdDM6dGVzdGU="))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
        assertEquals("Bearer", mockHttpServletResponse.getHeaderValue("Authorization").toString().substring(0, 6));
    }

    @DisplayName("Failed to authenticate due to wrong user and password")
    @Test
    void loginTest_fail_BadCredentialsException() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(post("/auth/login").header("Authorization", "Basic wrong"))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.UNAUTHORIZED.value(), mockHttpServletResponse.getStatus());
    }

}
