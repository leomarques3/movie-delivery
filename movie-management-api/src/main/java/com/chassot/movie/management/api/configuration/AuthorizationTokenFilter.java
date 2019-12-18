package com.chassot.movie.management.api.configuration;

import com.chassot.commons.constants.SecurityConstants;
import com.chassot.commons.enumerations.ErrorMessageEnum;
import com.chassot.commons.exceptions.SubjectIdNotFoundException;
import com.chassot.commons.utils.AuthUtils;
import com.chassot.entities.UserAccount;
import com.chassot.repositories.UserAccountRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class AuthorizationTokenFilter extends OncePerRequestFilter {

    private final UserAccountRepository userAccountRepository;
    private final String secret;

    public AuthorizationTokenFilter(UserAccountRepository userAccountRepository, String secret) {
        this.userAccountRepository = userAccountRepository;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        Boolean isValidToken = AuthUtils.isValidToken(secret, token);

        if (isValidToken) {
            UserAccount userAccount = getSubjectUser(token);
            authenticate(userAccount);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || header.isEmpty() || !header.startsWith(SecurityConstants.JWT_TOKEN_PREFIX)) {
            return null;
        }
        return header.substring(7);
    }

    private UserAccount getSubjectUser(String token) throws UnsupportedEncodingException, SubjectIdNotFoundException {
        String subjectId = Jwts.parser().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token)
                .getBody()
                .getSubject();

        Optional<UserAccount> userAccount = userAccountRepository.findById(Long.parseLong(subjectId));
        if (userAccount.isPresent()) {
            return userAccount.get();
        }
        throw new SubjectIdNotFoundException(ErrorMessageEnum.SUBJECT_NOT_FOUND.getMessage());
    }

    private void authenticate(UserAccount user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
