package com.example.licruitbackendjava.service;

import com.example.licruitbackendjava.auth.TokenProvider;
import com.example.licruitbackendjava.auth.dto.TokenDTO;
import com.example.licruitbackendjava.dto.user.LoginRequest;
import com.example.licruitbackendjava.dto.user.LoginResponse;
import com.example.licruitbackendjava.dto.user.RegisterRequest;
import com.example.licruitbackendjava.entity.TokenEntity;
import com.example.licruitbackendjava.entity.UserEntity;
import com.example.licruitbackendjava.exception.user.IncorrectPasswordException;
import com.example.licruitbackendjava.repository.TokenRepository;
import com.example.licruitbackendjava.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

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

    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByCompanyNumber(loginRequest.getCompanyNumber()).orElseThrow(IncorrectPasswordException::new);
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IncorrectPasswordException();
        }

        TokenDTO tokens = tokenProvider.createTokens(user);
        TokenEntity tokenEntity = TokenEntity.toTokenEntity(loginRequest.getCompanyNumber(), tokens.getRefreshToken());
        tokenRepository.save(tokenEntity);

        return LoginResponse.builder()
                .accessToken(tokens.getAccessToken())
                .refreshToken(tokens.getRefreshToken())
                .build();
    }
}
