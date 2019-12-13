package com.chassot.commons.enumerations;

public enum ErrorMessageEnum {

    MISUSE_UTIL_CLASS("Cannot create instance of static util class"),
    USERNAME_NOT_FOUND("There is no user with this username"),
    INVALID_CREDENTIALS("Username or password is invalid"),
    TOKEN_GENERATION_ERROR("An error occurred during token generation");

    private final String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
