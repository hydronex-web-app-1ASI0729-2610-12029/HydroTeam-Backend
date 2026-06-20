package com.tankiq.monitoring.interfaces.rest.transform;

import com.tankiq.monitoring.domain.model.aggregates.WaterLevelReading;
import com.tankiq.monitoring.interfaces.rest.resources.WaterLevelReadingResource;

public final class WaterLevelReadingResourceFromEntityAssembler {
    private WaterLevelReadingResourceFromEntityAssembler() {
    }

    public static WaterLevelReadingResource toResourceFromEntity(WaterLevelReading reading) {
        return new WaterLevelReadingResource(reading.getId(), reading.getLevelPercent(), reading.getVolumeLiters(), reading.getRecordedAt(), reading.getSensorId());
    }
}
