package com.example.licruitbackendjava.service;

import com.example.licruitbackendjava.dto.user.RegisterRequest;
import com.example.licruitbackendjava.entity.UserEntity;
import com.example.licruitbackendjava.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void checkCompanyNumberDuplicate(String companyNumber) {
        if(userRepository.existsByCompanyNumber(companyNumber)) {
            throw new DuplicateKeyException("이미 사용된 사업자번호입니다.");
        }
    }

    public void createUser(RegisterRequest registerRequest) {
        checkCompanyNumberDuplicate(registerRequest.getCompanyNumber());
        UserEntity userEntity = UserEntity.toUserEntity(registerRequest);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }
}
