package com.example.licruitbackendjava.exception.user;

import org.springframework.security.core.AuthenticationException;

public class IncorrectPasswordException extends AuthenticationException {
    public IncorrectPasswordException() {
        super("아이디 또는 비밀번호가 잘못되었습니다.");
    }
}
