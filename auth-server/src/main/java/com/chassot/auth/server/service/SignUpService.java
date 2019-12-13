package com.chassot.auth.server.service;

import com.chassot.commons.form.SignUpForm;

public interface SignUpService {

    void save(SignUpForm signUpForm);
    void autoLogin(String username, String password);

}
