package com.guru.depend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import java.io.Serial;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidJwtException(final String ex) {
        super(ex);
    }


}
