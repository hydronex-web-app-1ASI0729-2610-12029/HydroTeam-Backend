package com.tankiq.monitoring.interfaces.rest.resources;

import java.util.Date;

public record WaterLevelReadingResource(
        Long id,
        Double levelPercent,
        Double volumeLiters,
        Date recordedAt,
        Long sensorId
) {
}
