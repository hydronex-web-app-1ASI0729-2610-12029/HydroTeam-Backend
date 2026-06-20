package com.tankiq.notification.interfaces.rest.resources;

import com.tankiq.notification.domain.model.valueobjects.AlertStatus;
import com.tankiq.notification.domain.model.valueobjects.AlertType;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record CreateAlertResource(
        @NotNull AlertType type,
        @NotNull String message,
        @NotNull AlertStatus status,
        @NotNull Instant triggeredAt,
        Instant resolvedAt,
        @NotNull Long cisternId
) {
}