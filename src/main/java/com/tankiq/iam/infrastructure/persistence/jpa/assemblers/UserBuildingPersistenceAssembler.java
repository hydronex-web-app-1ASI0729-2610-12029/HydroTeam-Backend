package com.tankiq.iam.infrastructure.persistence.jpa.assemblers;

import com.tankiq.iam.domain.model.aggregates.UserBuilding;
import com.tankiq.iam.infrastructure.persistence.jpa.entities.UserBuildingPersistenceEntity;

public final class UserBuildingPersistenceAssembler {

    private UserBuildingPersistenceAssembler() {
    }

    public static UserBuilding toDomainFromPersistence(UserBuildingPersistenceEntity entity) {
        return new UserBuilding(
                entity.getId(),
                entity.getUserId(),
                entity.getBuildingId(),
                entity.getRole(),
                entity.getApartmentNumber(),
                entity.getAssociatedAt()
        );
    }

    public static UserBuildingPersistenceEntity toPersistenceFromDomain(UserBuilding userBuilding) {
        var entity = new UserBuildingPersistenceEntity();
        entity.setId(userBuilding.getId());
        entity.setUserId(userBuilding.getUserId());
        entity.setBuildingId(userBuilding.getBuildingId());
        entity.setRole(userBuilding.getRole());
        entity.setApartmentNumber(userBuilding.getApartmentNumber());
        entity.setAssociatedAt(userBuilding.getAssociatedAt());
        return entity;
    }
}
