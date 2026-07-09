package com.tankiq.monitoring.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record UpdateCisternResource(
        @NotNull Double capacityLiters,
        @NotNull Double currentLevelPercent,
        @NotNull Double alertThresholdPercent,
        @NotNull Long buildingId
) {
}
