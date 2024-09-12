package com.example.licruitbackendjava.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "tokens")
public class TokenEntity {

    @Id
    @Column(name = "user_company_number", length = 10, nullable = false)
    private String userCompanyNumber;

    @Column(name = "refresh_token", length = 300, nullable = false)
    private String refreshToken;

    public static TokenEntity toTokenEntity(String companyNumber, String refreshToken) {
        TokenEntity tokenEntity = new TokenEntity();

        tokenEntity.setUserCompanyNumber(companyNumber);
        tokenEntity.setRefreshToken(refreshToken);

        return tokenEntity;
    }
}
