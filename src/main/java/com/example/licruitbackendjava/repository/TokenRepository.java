package com.example.licruitbackendjava.repository;

import com.example.licruitbackendjava.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, String> {

}
