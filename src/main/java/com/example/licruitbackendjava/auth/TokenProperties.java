package com.example.licruitbackendjava.auth;

public record TokenProperties (
        String jwtIssuer,
        String jwtSecretKey,
        long jwtAccessTokenExpireTime,
        long jwtRefreshTokenExpireTime
) {

}
