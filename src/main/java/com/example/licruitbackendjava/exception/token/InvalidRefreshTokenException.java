package com.example.licruitbackendjava.exception.token;

import org.springframework.security.core.AuthenticationException;

public class InvalidRefreshTokenException extends AuthenticationException {
    public InvalidRefreshTokenException() {
        super("존재하지 않는 Refresh token 입니다.");
    }
}
