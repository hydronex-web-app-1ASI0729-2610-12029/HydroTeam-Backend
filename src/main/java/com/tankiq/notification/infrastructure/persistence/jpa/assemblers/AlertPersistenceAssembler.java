package com.tankiq.notification.infrastructure.persistence.jpa.assemblers;

import com.tankiq.notification.domain.model.aggregates.Alert;
import com.tankiq.notification.infrastructure.persistence.jpa.entities.AlertPersistenceEntity;

public final class AlertPersistenceAssembler {
    private AlertPersistenceAssembler() {
    }

    public static Alert toDomainFromPersistence(AlertPersistenceEntity entity) {
        return new Alert(
                entity.getId(),
                entity.getType(),
                entity.getMessage(),
                entity.getStatus(),
                entity.getTriggeredAt(),
                entity.getResolvedAt(),
                entity.getCisternId()
        );
    }

    public static AlertPersistenceEntity toPersistenceFromDomain(Alert alert) {
        var entity = new AlertPersistenceEntity();
        entity.setId(alert.getId());
        entity.setType(alert.getType());
        entity.setMessage(alert.getMessage());
        entity.setStatus(alert.getStatus());
        entity.setTriggeredAt(alert.getTriggeredAt());
        entity.setResolvedAt(alert.getResolvedAt());
        entity.setCisternId(alert.getCisternId());
        return entity;
    }
}
