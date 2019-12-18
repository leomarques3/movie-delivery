package com.chassot.commons.exceptions;

import java.sql.SQLException;

public class NoMoviesFoundException extends SQLException {

    public NoMoviesFoundException() {
        super();
    }

    public NoMoviesFoundException(String reason) {
        super(reason);
    }

}
