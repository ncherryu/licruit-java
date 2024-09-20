package com.example.licruitbackendjava.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(name = "company_number", unique = true, length = 10)
    private String companyNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectorId")
    private SectorEntity sector;

    @Column(name = "img", nullable = true)
    private String img;

    @Column(name = "is_marketing", nullable = false)
    private Boolean isMarketing;
}
