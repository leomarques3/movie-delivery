package com.chassot.auth.server.service.impl;

import com.chassot.auth.server.model.form.SignUpForm;
import com.chassot.auth.server.service.SignUpService;
import com.chassot.commons.enumerations.ErrorMessageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmpty;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class SignUpValidatorImpl implements Validator {

    private final SignUpService userSignUpServiceImpl;

    @Autowired
    public SignUpValidatorImpl(SignUpService userSignUpServiceImpl) {
        this.userSignUpServiceImpl = userSignUpServiceImpl;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpForm signUpForm = (SignUpForm) target;

        rejectIfEmpty(errors, "firstName", "firstName.empty", ErrorMessageEnum.FIELD_EMPTY.getMessage());
        rejectIfEmpty(errors, "lastName", "lastName.empty", ErrorMessageEnum.FIELD_EMPTY.getMessage());
        rejectIfEmptyOrWhitespace(errors, "username", "username.empty.invalid", ErrorMessageEnum.FIELD_EMPTY_OR_INVALID.getMessage());
        rejectIfEmptyOrWhitespace(errors, "email", "email.empty.invalid", ErrorMessageEnum.FIELD_EMPTY_OR_INVALID.getMessage());
        rejectIfEmptyOrWhitespace(errors, "password", "password.empty.invalid", ErrorMessageEnum.FIELD_EMPTY_OR_INVALID.getMessage());

        if (userSignUpServiceImpl.findByUsername(signUpForm.getUsername()) != null) {
            errors.rejectValue("username", "username.duplicate", ErrorMessageEnum.USERNAME_ALREADY_EXISTS.getMessage());
        }

        if (userSignUpServiceImpl.findByEmail(signUpForm.getEmail()) != null) {
            errors.rejectValue("email", "email.duplicate", ErrorMessageEnum.EMAIL_ALREADY_EXISTS.getMessage());
        }

    }

}
