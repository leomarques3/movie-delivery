package com.chassot.auth.server.service;

import com.chassot.auth.server.service.impl.UserDetailsServiceImpl;
import com.chassot.entities.UserAccount;
import com.chassot.repositories.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UserDetailsService.class})
class UserDetailsServiceTest {

    private UserAccount userAccount;
    private UserDetailsService userDetailsService;

    @Mock
    private UserAccountRepository userAccountRepository;

    @BeforeEach
    void initEach() {
        userAccount = new UserAccount();
        userAccount.setId(1L);
        userAccount.setUsername("leomarques");

        userDetailsService = new UserDetailsServiceImpl(userAccountRepository);
    }

    @DisplayName("Successfully load user from the database")
    @Test
    void loadUserByUsernameTest_success() {
        String username = "leomarques";
        when(userAccountRepository.findByUsername(anyString())).thenReturn(Optional.of(userAccount));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        assertEquals(username, userDetails.getUsername());
    }

    @DisplayName("Did not find an user")
    @Test
    void loadUserByUsernameTest_fail_UsernameNotFoundException() {
        String username = "wronguser";
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
    }

}
