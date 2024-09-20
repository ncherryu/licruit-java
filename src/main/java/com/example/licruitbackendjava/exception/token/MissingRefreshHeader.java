package com.example.licruitbackendjava.exception.token;

import org.springframework.security.core.AuthenticationException;

public class MissingRefreshHeader extends AuthenticationException {
    public MissingRefreshHeader() {
        super("Refresh 토큰 필요");
    }
}
