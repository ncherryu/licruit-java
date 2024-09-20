package com.example.licruitbackendjava.repository;

import com.example.licruitbackendjava.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    Optional<TokenEntity> findByUserCompanyNumber(String companyNumber);
}
