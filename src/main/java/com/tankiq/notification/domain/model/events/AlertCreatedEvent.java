package com.tankiq.notification.domain.model.events;

import com.tankiq.notification.domain.model.aggregates.Alert;
import com.tankiq.notification.domain.model.valueobjects.AlertStatus;
import com.tankiq.notification.domain.model.valueobjects.AlertType;
import java.time.Instant;

public record AlertCreatedEvent(
        Long alertId,
        AlertType type,
        String message,
        AlertStatus status,
        Instant triggeredAt,
        Instant resolvedAt,
        Long cisternId
) {
    public static AlertCreatedEvent from(Alert alert) {
        return new AlertCreatedEvent(
                alert.getId(),
                alert.getType(),
                alert.getMessage(),
                alert.getStatus(),
                alert.getTriggeredAt(),
                alert.getResolvedAt(),
                alert.getCisternId()
        );
    }
}