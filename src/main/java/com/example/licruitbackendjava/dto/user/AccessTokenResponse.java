package com.example.licruitbackendjava.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessTokenResponse {
    String accessToken;
}
