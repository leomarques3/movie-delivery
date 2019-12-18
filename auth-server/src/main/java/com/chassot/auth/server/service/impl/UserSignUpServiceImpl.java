package com.chassot.auth.server.service.impl;

import com.chassot.auth.server.model.form.SignUpForm;
import com.chassot.auth.server.service.SignUpService;
import com.chassot.entities.UserAccount;
import com.chassot.repositories.UserAccountRepository;
import com.chassot.repositories.UserAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserSignUpServiceImpl implements SignUpService {

    private final UserAuthorityRepository userAuthorityRepository;
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserSignUpServiceImpl(UserAuthorityRepository userAuthorityRepository, UserAccountRepository userAccountRepository) {
        this.userAuthorityRepository = userAuthorityRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount findByUsername(String username) {
        return userAccountRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserAccount findByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }

    @Override
    public void save(SignUpForm signUpForm) {
        userAccountRepository.save(convertToEntity(signUpForm));
    }

    private UserAccount convertToEntity(SignUpForm signUpForm) {
        UserAccount userAccount = new UserAccount(signUpForm.getFirstName(), signUpForm.getLastName(), signUpForm.getUsername(), signUpForm.getEmail());
        userAccount.setPassword(new BCryptPasswordEncoder().encode(signUpForm.getPassword()));
        userAccount.setAuthorities(new HashSet<>(userAuthorityRepository.findByAuthority("user")));
        return userAccount;
    }

}
