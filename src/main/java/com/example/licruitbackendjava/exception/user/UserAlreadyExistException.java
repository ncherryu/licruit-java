package com.example.licruitbackendjava.exception.user;

import org.springframework.dao.DuplicateKeyException;

public class UserAlreadyExistException extends DuplicateKeyException {

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
