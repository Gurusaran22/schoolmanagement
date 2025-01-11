package com.guru.depend.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class IllegalArgumentException extends AuthenticationException {
    @Serial
    private static final long serialVersionUID = 1L;
    public IllegalArgumentException(String m) {super(m);
    }
}
