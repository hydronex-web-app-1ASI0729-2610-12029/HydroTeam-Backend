package com.tankiq.notification.interfaces.rest.resources;

import com.tankiq.notification.domain.model.valueobjects.AlertStatus;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record UpdateAlertResource(
        @NotNull AlertStatus status,
        Instant resolvedAt
) {}