package com.chassot.commons.enumerations;

public enum ErrorMessageEnum {

    MISUSE_UTIL_CLASS("Cannot create instance of static util class"),
    USERNAME_NOT_FOUND("There is no user with this username");

    private final String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
