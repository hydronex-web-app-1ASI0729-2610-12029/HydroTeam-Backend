package com.tankiq.monitoring.domain.model.commands;

import java.util.Date;

public record CreateWaterLevelReadingCommand(
        Double levelPercent,
        Double volumeLiters,
        Date recordedAt,
        Long sensorId
) {
}
