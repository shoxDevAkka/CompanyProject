package com.company.security;

import org.springframework.security.core.AuthenticationException;

public class OptionalCaseException extends AuthenticationException {

    public OptionalCaseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public OptionalCaseException(String msg) {
        super(msg);
    }
}
