package com.tankiq.monitoring.domain.model.events;

import com.tankiq.monitoring.domain.model.aggregates.Sensor;

public record SensorCreatedEvent(Long sensorId, String hardwareId) {
    public static SensorCreatedEvent from(Sensor sensor) {
        return new SensorCreatedEvent(sensor.getId(), sensor.getHardwareId());
    }
}
