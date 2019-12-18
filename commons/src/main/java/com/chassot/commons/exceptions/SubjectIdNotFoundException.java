package com.chassot.commons.exceptions;

import java.io.IOException;

public class SubjectIdNotFoundException extends IOException {

    public SubjectIdNotFoundException() {
        super();
    }

    public SubjectIdNotFoundException(String message) {
        super(message);
    }

}
