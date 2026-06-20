package com.tankiq.notification.interfaces.rest.resources;

import com.tankiq.notification.domain.model.valueobjects.AlertStatus;
import com.tankiq.notification.domain.model.valueobjects.AlertType;
import java.time.Instant;

public record AlertResource(
        Long id,
        AlertType type,
        String message,
        AlertStatus status,
        Instant triggeredAt,
        Instant resolvedAt,
        Long cisternId
) {
}