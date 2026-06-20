package com.tankiq.iam.domain.repositories;

import com.tankiq.iam.domain.model.aggregates.UserBuilding;

import java.util.List;
import java.util.Optional;

public interface UserBuildingRepository {
    Optional<UserBuilding> findById(Long id);
    List<UserBuilding> findAll();
    UserBuilding save(UserBuilding userBuilding);
}
