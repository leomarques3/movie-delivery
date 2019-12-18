package com.chassot.auth.server.controller;

import com.chassot.commons.enumerations.ErrorMessageEnum;
import com.chassot.entities.UserAccount;
import com.chassot.repositories.UserAccountRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
class AuthControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserAccountRepository userAccountRepository;

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
    void loginTest_fail_BadCredentials() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(post("/auth/login").header("Authorization", "Basic wrong"))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.UNAUTHORIZED.value(), mockHttpServletResponse.getStatus());
    }


    @DisplayName("Successfully created a new user")
    @Test
    void signUp_success() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(
                        post("/auth/signUp")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"firstName\": \"New\", \"lastName\": \"Test\", \"username\": \"newtest\", \"email\": \"newtest@teste.com\", \"password\": \"teste\" }")
                )
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.CREATED.value(), mockHttpServletResponse.getStatus());
    }

    @DisplayName("Failed to validate request body")
    @Test
    void signUp_fail_MethodArgumentNotValid_general() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(
                        post("/auth/signUp")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"firstName\": \"\", \"lastName\": \"da Silva\", \"username\": \"josilva\", \"email\": \"josilva@teste.com\", \"password\": \"teste\" }")
                )
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mockHttpServletResponse.getStatus());
    }

    @DisplayName("Failed to validate request body due to duplicate user")
    @Test
    void signUp_fail_MethodArgumentNotValid_duplicateUsername() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(
                        post("/auth/signUp")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"firstName\": \"Joao\", \"lastName\": \"da Silva\", \"username\": \"leochassot3\", \"email\": \"josilva@teste.com\", \"password\": \"teste\" }")
                )
                .andReturn()
                .getResponse();
        String content = mockHttpServletResponse.getContentAsString();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mockHttpServletResponse.getStatus());
        assertTrue(content.contains(ErrorMessageEnum.USERNAME_ALREADY_EXISTS.getMessage()));
    }

    @DisplayName("Failed to validate request body due to duplicate email")
    @Test
    void signUp_fail_MethodArgumentNotValid_duplicateEmail() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(
                        post("/auth/signUp")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"firstName\": \"Joao\", \"lastName\": \"da Silva\", \"username\": \"josilva\", \"email\": \"john.doe@test.com\", \"password\": \"teste\" }")
                )
                .andReturn()
                .getResponse();
        String content = mockHttpServletResponse.getContentAsString();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mockHttpServletResponse.getStatus());
        assertTrue(content.contains(ErrorMessageEnum.EMAIL_ALREADY_EXISTS.getMessage()));
    }

    @AfterEach
    void cleanUp() {
        UserAccount userAccount = userAccountRepository.findByEmail("newtest@teste.com");
        if (userAccount != null) {
            userAccountRepository.delete(userAccount);
        }
    }

}
