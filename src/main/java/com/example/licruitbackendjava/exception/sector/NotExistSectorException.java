package com.example.licruitbackendjava.exception.sector;

import java.util.NoSuchElementException;

public class NotExistSectorException extends NoSuchElementException {
    public NotExistSectorException() {
        super("존재하지 않는 업종입니다.");
    }
}
