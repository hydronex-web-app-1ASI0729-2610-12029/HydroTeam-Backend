package com.tankiq.monitoring.interfaces.rest.resources;

import com.tankiq.monitoring.domain.model.enums.SensorStatus;
import com.tankiq.monitoring.domain.model.enums.SensorType;

import java.util.Date;

public record SensorResource(
        Long id,
        String hardwareId,
        SensorType type,
        SensorStatus status,
        Date lastSyncAt,
        Long cisternId
) {
}
