package com.tankiq.monitoring.interfaces.rest.resources;

import com.tankiq.monitoring.domain.model.enums.SensorStatus;
import com.tankiq.monitoring.domain.model.enums.SensorType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UpdateSensorResource(
        @NotBlank String hardwareId,
        @NotNull SensorType type,
        @NotNull SensorStatus status,
        Date lastSyncAt,
        @NotNull Long cisternId
) {
}
