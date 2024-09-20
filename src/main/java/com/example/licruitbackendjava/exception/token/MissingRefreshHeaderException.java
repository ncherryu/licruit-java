package com.example.licruitbackendjava.exception.token;

import org.springframework.security.core.AuthenticationException;

public class MissingRefreshHeaderException extends AuthenticationException {
    public MissingRefreshHeaderException() {
        super("Refresh 토큰 필요");
    }
}
