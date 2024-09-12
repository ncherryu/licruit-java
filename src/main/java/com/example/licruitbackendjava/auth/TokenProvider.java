package com.example.licruitbackendjava.auth;

import com.example.licruitbackendjava.auth.dto.TokenDTO;
import com.example.licruitbackendjava.entity.UserEntity;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenProvider {

    private final String issuer;
    private final SecretKey secretKey;
    private final long accessTokenExpireTime;
    private final long refreshTokenExpireTime;
    private final JwtParser jwtParser;

    public TokenProvider(
            @Value("${spring.jwt.issuer}") String issuer,
            @Value("${spring.jwt.secret}") String secretKey,
            @Value("${spring.jwt.accessTokenExpireTime}") long accessTokenExpireTime,
            @Value("${spring.jwt.refreshTokenExpireTime}") long refreshTokenExpireTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.issuer = issuer;
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpireTime = accessTokenExpireTime;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
        this.jwtParser = Jwts.parserBuilder().setSigningKey(keyBytes).requireIssuer(issuer).build();
    }

    public String createAccessToken(UserEntity userEntity) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + accessTokenExpireTime);

        return Jwts.builder()
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .claim("companyNumber", userEntity.getCompanyNumber())
                .claim("tokenType", "accessToken")
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(UserEntity userEntity) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + refreshTokenExpireTime);

        return Jwts.builder()
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .claim("companyNumber", userEntity.getCompanyNumber())
                .claim("tokenType", "refreshToken")
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public TokenDTO createTokens(UserEntity userEntity) {
        return TokenDTO.builder()
                .accessToken(createAccessToken(userEntity))
                .refreshToken(createRefreshToken(userEntity))
                .build();
    }
}
