package com.tankiq.monitoring.infrastructure.persistence.jpa.assemblers;

import com.tankiq.monitoring.domain.model.aggregates.WaterLevelReading;
import com.tankiq.monitoring.infrastructure.persistence.jpa.entities.WaterLevelReadingPersistenceEntity;

public final class WaterLevelReadingPersistenceAssembler {
    private WaterLevelReadingPersistenceAssembler() {
    }

    public static WaterLevelReading toDomainFromPersistence(WaterLevelReadingPersistenceEntity entity) {
        return new WaterLevelReading(
                entity.getId(),
                entity.getLevelPercent(),
                entity.getVolumeLiters(),
                entity.getRecordedAt(),
                entity.getSensorId()
        );
    }

    public static WaterLevelReadingPersistenceEntity toPersistenceFromDomain(WaterLevelReading reading) {
        var entity = new WaterLevelReadingPersistenceEntity();
        entity.setId(reading.getId());
        entity.setLevelPercent(reading.getLevelPercent());
        entity.setVolumeLiters(reading.getVolumeLiters());
        entity.setRecordedAt(reading.getRecordedAt());
        entity.setSensorId(reading.getSensorId());
        return entity;
    }
}
