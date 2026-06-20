package com.tankiq.monitoring.interfaces.rest.transform;

import com.tankiq.monitoring.domain.model.aggregates.Sensor;
import com.tankiq.monitoring.interfaces.rest.resources.SensorResource;

public final class SensorResourceFromEntityAssembler {
    private SensorResourceFromEntityAssembler() {
    }

    public static SensorResource toResourceFromEntity(Sensor sensor) {
        return new SensorResource(sensor.getId(), sensor.getHardwareId(), sensor.getType(), sensor.getStatus(), sensor.getLastSyncAt(), sensor.getCisternId());
    }
}
