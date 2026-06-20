package com.tankiq.notification.domain.model.commands;

import com.tankiq.notification.domain.model.valueobjects.AlertStatus;
import com.tankiq.notification.domain.model.valueobjects.AlertType;
import java.time.Instant;

public record CreateAlertCommand(
        AlertType type,
        String message,
        AlertStatus status,
        Instant triggeredAt,
        Instant resolvedAt,
        Long cisternId
) {
}