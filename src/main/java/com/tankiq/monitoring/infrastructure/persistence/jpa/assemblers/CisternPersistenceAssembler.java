package com.tankiq.monitoring.infrastructure.persistence.jpa.assemblers;

import com.tankiq.monitoring.domain.model.aggregates.Cistern;
import com.tankiq.monitoring.infrastructure.persistence.jpa.entities.CisternPersistenceEntity;

public final class CisternPersistenceAssembler {
    private CisternPersistenceAssembler() {
    }

    public static Cistern toDomainFromPersistence(CisternPersistenceEntity entity) {
        return new Cistern(
                entity.getId(),
                entity.getCapacityLiters(),
                entity.getCurrentLevelPercent(),
                entity.getAlertThresholdPercent(),
                entity.getBuildingId()
        );
    }

    public static CisternPersistenceEntity toPersistenceFromDomain(Cistern cistern) {
        var entity = new CisternPersistenceEntity();
        entity.setId(cistern.getId());
        entity.setCapacityLiters(cistern.getCapacityLiters());
        entity.setCurrentLevelPercent(cistern.getCurrentLevelPercent());
        entity.setAlertThresholdPercent(cistern.getAlertThresholdPercent());
        entity.setBuildingId(cistern.getBuildingId());
        return entity;
    }
}
