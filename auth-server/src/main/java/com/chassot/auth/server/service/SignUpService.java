package com.chassot.auth.server.service;

import com.chassot.auth.server.model.form.SignUpForm;
import com.chassot.entities.UserAccount;

public interface SignUpService {

    UserAccount findByUsername(String username);
    UserAccount findByEmail(String email);
    void save(SignUpForm signUpForm);

}
