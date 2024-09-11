package com.example.licruitbackendjava.repository;

import com.example.licruitbackendjava.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByCompanyNumber(String companyNumber);
}
