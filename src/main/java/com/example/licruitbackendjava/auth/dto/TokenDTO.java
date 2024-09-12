package com.example.licruitbackendjava.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenDTO {

        @NotBlank
        String accessToken;

        @NotBlank
        String refreshToken;
}
