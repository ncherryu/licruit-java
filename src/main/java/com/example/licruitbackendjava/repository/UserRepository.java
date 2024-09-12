package com.example.licruitbackendjava.repository;

import com.example.licruitbackendjava.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByCompanyNumber(String companyNumber);

    Optional<UserEntity> findByCompanyNumber(String companyNumber);
}
