package com.tankiq.notification.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record CreateAlertResource(
        @NotBlank String type,
        @NotBlank String message,
        @NotBlank String status,
        @NotNull Instant triggeredAt,
        Instant resolvedAt,
        @NotNull Long cisternId
) {
}
