package com.chassot.auth.server.service;

import com.chassot.auth.server.service.impl.JwtTokenServiceImpl;
import com.chassot.entities.UserAccount;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@SpringBootTest(classes = {TokenService.class})
class TokenServiceTest {

    private UserAccount userAccount;
    private TokenService tokenService;
    private final String secret = "MbQeThWmZq4t7w!z%C*F-JaNdRfUjXn2r5u8x/A?D(G+KbPeShVkYp3s6v9y$B&E";

    @Mock
    private Authentication authentication;

    @BeforeEach
    void initEach() {
        userAccount = new UserAccount();
        userAccount.setId(1L);
        tokenService = new JwtTokenServiceImpl();
        setField(tokenService, "secret", secret);
    }

    @DisplayName("Successfully generated jwt token")
    @Test
    public void generateTokenTest_success() throws UnsupportedEncodingException {
        when(authentication.getPrincipal()).thenReturn(userAccount);
        String token = tokenService.generateToken(authentication);
        String subId = getSubIdFromToken(token);
        assertEquals(userAccount.getId().toString(), subId);
    }

    private String getSubIdFromToken(String token) throws UnsupportedEncodingException {
        return Jwts.parser().setSigningKey(secret.getBytes(StandardCharsets.UTF_8.name())).parseClaimsJws(token).getBody().getSubject();
    }

}
