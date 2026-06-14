package com.tankiq.notification.domain.model.events;

import com.tankiq.notification.domain.model.aggregates.Alert;
import java.time.Instant;

public record AlertCreatedEvent(
        Long alertId,
        String type,
        String message,
        String status,
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
