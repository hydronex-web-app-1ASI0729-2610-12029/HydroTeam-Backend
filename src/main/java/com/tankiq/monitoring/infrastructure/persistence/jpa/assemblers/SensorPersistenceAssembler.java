package com.tankiq.monitoring.infrastructure.persistence.jpa.assemblers;

import com.tankiq.monitoring.domain.model.aggregates.Sensor;
import com.tankiq.monitoring.infrastructure.persistence.jpa.entities.SensorPersistenceEntity;

public final class SensorPersistenceAssembler {
    private SensorPersistenceAssembler() {
    }

    public static Sensor toDomainFromPersistence(SensorPersistenceEntity entity) {
        return new Sensor(
                entity.getId(),
                entity.getHardwareId(),
                entity.getType(),
                entity.getStatus(),
                entity.getLastSyncAt(),
                entity.getCisternId()
        );
    }

    public static SensorPersistenceEntity toPersistenceFromDomain(Sensor sensor) {
        var entity = new SensorPersistenceEntity();
        entity.setId(sensor.getId());
        entity.setHardwareId(sensor.getHardwareId());
        entity.setType(sensor.getType());
        entity.setStatus(sensor.getStatus());
        entity.setLastSyncAt(sensor.getLastSyncAt());
        entity.setCisternId(sensor.getCisternId());
        return entity;
    }
}
