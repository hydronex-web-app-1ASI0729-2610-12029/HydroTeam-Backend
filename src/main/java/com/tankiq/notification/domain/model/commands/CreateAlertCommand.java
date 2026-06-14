package com.tankiq.notification.domain.model.commands;

import java.time.Instant;
public record CreateAlertCommand(
        String type,
        String message,
        String status,
        Instant triggeredAt,
        Instant resolvedAt,
        Long cisternId
) {
}
