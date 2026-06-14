package com.tankiq.monitoring.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCisternResource(
        @NotNull Double capacityLiters,
        @NotNull Double currentLevelPercent,
        @NotNull Double alertThresholdPercent,
        @NotNull Long buildingId
) {
}
