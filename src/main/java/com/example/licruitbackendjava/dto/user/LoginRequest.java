package com.example.licruitbackendjava.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "사업자번호 필요")
    String companyNumber;

    @NotBlank(message = "비밀번호 필요")
    String password;
}
