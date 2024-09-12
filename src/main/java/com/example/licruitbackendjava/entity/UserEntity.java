package com.example.licruitbackendjava.entity;

import com.example.licruitbackendjava.dto.user.RegisterRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "company_number", length = 10)
    private String companyNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "sector_id", nullable = false)
    private int sectorId;

    @Column(name = "img", nullable = true)
    private String img;

    @Column(name = "is_marketing", nullable = false)
    private Boolean isMarketing;

    public static UserEntity toUserEntity(RegisterRequest registerRequest) {
        UserEntity userEntity = new UserEntity();

        userEntity.setCompanyNumber(registerRequest.getCompanyNumber());
        userEntity.setPassword(registerRequest.getPassword());
        userEntity.setBusinessName(registerRequest.getBusinessName());
        userEntity.setContact(registerRequest.getContact());
        userEntity.setAddress(registerRequest.getAddress());
        userEntity.setSectorId(registerRequest.getSectorId());
        userEntity.setImg(null);
        userEntity.setIsMarketing(registerRequest.getIsMarketing());

        return userEntity;
    }
}
