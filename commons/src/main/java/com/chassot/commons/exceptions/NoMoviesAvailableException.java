package com.chassot.commons.exceptions;

import java.sql.SQLException;

public class NoMoviesAvailableException extends SQLException {

    public NoMoviesAvailableException() {
        super();
    }

    public NoMoviesAvailableException(String reason) {
        super(reason);
    }
}
