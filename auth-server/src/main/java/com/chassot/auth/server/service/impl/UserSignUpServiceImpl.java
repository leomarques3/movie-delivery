package com.chassot.auth.server.service.impl;

import com.chassot.auth.server.service.SignUpService;
import com.chassot.commons.form.SignUpForm;
import com.chassot.entities.UserAccount;
import com.chassot.entities.UserAuthority;
import com.chassot.repositories.UserAccountRepository;
import com.chassot.repositories.UserAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserSignUpServiceImpl implements SignUpService {

    private final UserAuthorityRepository userAuthorityRepository;
    private final UserAccountRepository userAccountRepository;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserSignUpServiceImpl(UserAuthorityRepository userAuthorityRepository,
                                 UserAccountRepository userAccountRepository, UserDetailsService userDetailsService,
                                 AuthenticationManager authenticationManager) {
        this.userAuthorityRepository = userAuthorityRepository;
        this.userAccountRepository = userAccountRepository;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void save(SignUpForm signUpForm) {
        userAccountRepository.save(convertToEntity(signUpForm));
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    private UserAccount convertToEntity(SignUpForm signUpForm) {
        UserAccount userAccount = new UserAccount(signUpForm.getFirstName(), signUpForm.getLastName(), signUpForm.getUsername(), signUpForm.getEmail());
        userAccount.setPassword(new BCryptPasswordEncoder().encode(signUpForm.getPassword()));
        userAccount.setAuthorities(new HashSet<>(userAuthorityRepository.findByAuthority("user")));
        return userAccount;
    }

}
