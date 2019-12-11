package com.chassot.auth.server.service.impl;

import com.chassot.auth.server.service.TokenService;
import com.chassot.commons.constants.SecurityConstants;
import com.chassot.entities.UserAccount;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenServiceImpl implements TokenService {

    @Value("${movie-delivery.security.secret}")
    private String secret;

    @Override
    public String generateToken(Authentication authentication) {
        UserAccount loggedInUserAccount = (UserAccount) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + SecurityConstants.JWT_TOKEN_EXPIRATION);
        byte[] secretKey = secret.getBytes();
        return Jwts
                .builder()
                .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.JWT_TOKEN_TYPE)
                .setSubject(loggedInUserAccount.getId().toString())
                .setIssuer(SecurityConstants.JWT_TOKEN_ISSUER)
                .setAudience(SecurityConstants.JWT_TOKEN_AUDIENCE)
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .compact();
    }

}
