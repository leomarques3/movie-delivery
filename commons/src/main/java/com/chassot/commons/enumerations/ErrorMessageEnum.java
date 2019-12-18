package com.chassot.commons.enumerations;

public enum ErrorMessageEnum {

    MISUSE_UTIL_CLASS("Cannot create instance of static util class"),
    USERNAME_NOT_FOUND("There is no user with this username"),
    FIELD_EMPTY("The provided field is empty"),
    FIELD_EMPTY_OR_INVALID("The provided field is empty or invalid"),
    USERNAME_ALREADY_EXISTS("There already is a user with this username"),
    EMAIL_ALREADY_EXISTS("There already is a user with this email"),
    SUBJECT_NOT_FOUND("There is no user for the current subject id"),
    NO_MOVIES_AVAILABLE("There are no movies available"),
    NO_MOVIES_FOUND("There are no movies with the given name");

    private final String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
