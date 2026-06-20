package com.tankiq.monitoring.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateWaterLevelReadingResource(
        @NotNull Double levelPercent,
        @NotNull Double volumeLiters,
        @NotNull Date recordedAt,
        @NotNull Long sensorId
) {
}
