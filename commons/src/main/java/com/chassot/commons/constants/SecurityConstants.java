package com.chassot.commons.constants;

import com.chassot.commons.enumerations.ErrorMessageEnum;

public final class SecurityConstants {

    public static final Long JWT_TOKEN_EXPIRATION = 86400000L;
    public static final String JWT_TOKEN_TYPE = "JWT";
    public static final String JWT_TOKEN_ISSUER = "auth-server";
    public static final String JWT_TOKEN_AUDIENCE = "movie-delivery";
    public static final String JWT_TOKEN_PREFIX = "Bearer";

    private SecurityConstants() {
        throw new IllegalStateException(ErrorMessageEnum.MISUSE_UTIL_CLASS.getMessage());
    }

}
