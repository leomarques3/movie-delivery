package com.chassot.auth.server.service;

import com.chassot.auth.server.model.form.SignUpForm;
import com.chassot.auth.server.service.impl.SignUpValidatorImpl;
import com.chassot.entities.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {Validator.class})
class ValidatorTest {

    private Validator validator;
    private SignUpForm signUpForm;
    private Errors errors;
    private UserAccount userAccount;

    @Mock
    private SignUpService userSignUpServiceImpl;

    @BeforeEach
    void initEach() {
        signUpForm = new SignUpForm("John", "Doe", "johndoe", "john.doe@test.com", "test");
        validator = new SignUpValidatorImpl(userSignUpServiceImpl);
        errors = new BeanPropertyBindingResult(signUpForm, "signUpForm");
        userAccount = new UserAccount();
    }

    @DisplayName("Successfully validated request body")
    @Test
    void validateTest_success() {
        when(userSignUpServiceImpl.findByUsername(any())).thenReturn(null);
        when(userSignUpServiceImpl.findByEmail(any())).thenReturn(null);
        validator.validate(signUpForm, errors);
        assertFalse(errors.hasErrors());
    }

    @DisplayName("Failed to validate request body due to duplicate username")
    @Test
    void validateTest_fail_duplicateUsername() {
        when(userSignUpServiceImpl.findByUsername(any())).thenReturn(userAccount);
        when(userSignUpServiceImpl.findByEmail(any())).thenReturn(null);
        validator.validate(signUpForm, errors);
        assertTrue(errors.hasErrors());
    }

    @DisplayName("Failed to validate request body due to duplicate email")
    @Test
    void validateTest_fail_duplicateEmail() {
        when(userSignUpServiceImpl.findByUsername(any())).thenReturn(null);
        when(userSignUpServiceImpl.findByEmail(any())).thenReturn(userAccount);
        validator.validate(signUpForm, errors);
        assertTrue(errors.hasErrors());
    }

    @DisplayName("Test method added ONLY to cover supports(Class<?> clazz) invocation not covered by application tests")
    @Test
    void supportsTest() {
        assertTrue(validator.supports(SignUpForm.class));
        assertFalse(validator.supports(Object.class));
    }

}
