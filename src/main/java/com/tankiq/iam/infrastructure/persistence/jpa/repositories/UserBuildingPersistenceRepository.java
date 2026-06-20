package com.tankiq.iam.infrastructure.persistence.jpa.repositories;

import com.tankiq.iam.infrastructure.persistence.jpa.entities.UserBuildingPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBuildingPersistenceRepository extends JpaRepository<UserBuildingPersistenceEntity, Long> {
    List<UserBuildingPersistenceEntity> findByUserId(Long userId);
    List<UserBuildingPersistenceEntity> findByBuildingId(Long buildingId);
}
