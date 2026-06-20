package com.tankiq.monitoring.domain.model.events;

import com.tankiq.monitoring.domain.model.aggregates.WaterLevelReading;

public record WaterLevelReadingCreatedEvent(Long readingId, Long sensorId) {
    public static WaterLevelReadingCreatedEvent from(WaterLevelReading reading) {
        return new WaterLevelReadingCreatedEvent(reading.getId(), reading.getSensorId());
    }
}
