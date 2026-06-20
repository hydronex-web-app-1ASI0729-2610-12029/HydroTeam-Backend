package com.tankiq.monitoring.domain.model.commands;

import com.tankiq.monitoring.domain.model.enums.SensorStatus;
import com.tankiq.monitoring.domain.model.enums.SensorType;

import java.util.Date;

public record CreateSensorCommand(
        String hardwareId,
        SensorType type,
        SensorStatus status,
        Date lastSyncAt,
        Long cisternId
) {
}
