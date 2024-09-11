package com.example.licruitbackendjava.dto.user;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "사업자번호 확인 필요")
    @Size(min = 10, max = 10, message = "사업자번호 길이 확인 필요")
    private String companyNumber;

    @NotBlank(message = "비밀번호 확인 필요")
    private String password;

    @NotBlank(message = "상호명 확인 필요")
    private String businessName;

    @NotBlank(message = "연락처 확인 필요")
    private String contact;

    @NotBlank(message = "주소 확인 필요")
    private String address;

    @Min(value = 1, message = "업종 번호 확인 필요")
    private int sectorId;

    @NotNull(message = "마케팅 동의 여부 확인 필요")
    private Boolean isMarketing;
}
