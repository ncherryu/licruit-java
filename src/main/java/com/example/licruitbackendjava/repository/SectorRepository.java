package com.example.licruitbackendjava.repository;

import com.example.licruitbackendjava.entity.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<SectorEntity, Integer> {
    Optional<SectorEntity> findById(int sectorId);
}
