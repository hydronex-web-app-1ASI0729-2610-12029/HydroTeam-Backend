package com.tankiq.monitoring.infrastructure.persistence.jpa.assemblers;

import com.tankiq.monitoring.domain.model.aggregates.Building;
import com.tankiq.monitoring.infrastructure.persistence.jpa.entities.BuildingPersistenceEntity;

public final class BuildingPersistenceAssembler {
    private BuildingPersistenceAssembler() {
    }

    public static Building toDomainFromPersistence(BuildingPersistenceEntity entity) {
        return new Building(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getDistrict()
        );
    }

    public static BuildingPersistenceEntity toPersistenceFromDomain(Building building) {
        var entity = new BuildingPersistenceEntity();
        entity.setId(building.getId());
        entity.setName(building.getName());
        entity.setAddress(building.getAddress());
        entity.setDistrict(building.getDistrict());
        return entity;
    }
}
