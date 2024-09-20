package com.example.licruitbackendjava.service;

import com.example.licruitbackendjava.auth.TokenProvider;
import com.example.licruitbackendjava.dto.user.AccessTokenResponse;
import com.example.licruitbackendjava.entity.TokenEntity;
import com.example.licruitbackendjava.entity.UserEntity;
import com.example.licruitbackendjava.exception.common.NotFoundException;
import com.example.licruitbackendjava.exception.token.InvalidRefreshTokenException;
import com.example.licruitbackendjava.repository.TokenRepository;
import com.example.licruitbackendjava.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;

    public void compareRefreshToken(String refreshToken, String companyNumber) {
        TokenEntity tokenEntity = tokenRepository.findByUserCompanyNumber(companyNumber).orElseThrow(InvalidRefreshTokenException::new);
        if(!refreshToken.equals(tokenEntity.getRefreshToken())) {
            throw new InvalidRefreshTokenException();
        }
    }

    public AccessTokenResponse createNewAccessToken(String refreshToken) {
        // JWT 디코딩
        String companyNumber = tokenProvider.getCompanyNumberFromToken(refreshToken);

        // DB에 저장된 refresh token과 일치하는지 확인
        compareRefreshToken(refreshToken, companyNumber);

        // 새로운 access token 생성
        UserEntity userEntity = userRepository.findByCompanyNumber(companyNumber).orElseThrow(() -> new NotFoundException("존재하지 않는 사용자입니다."));
        String accessToken =  tokenProvider.createAccessToken(userEntity);

        return AccessTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }

}
