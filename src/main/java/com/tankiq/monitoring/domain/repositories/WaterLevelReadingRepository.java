package com.tankiq.monitoring.domain.repositories;

import com.tankiq.monitoring.domain.model.aggregates.WaterLevelReading;

import java.util.List;
import java.util.Optional;

public interface WaterLevelReadingRepository {
    Optional<WaterLevelReading> findById(Long id);
    List<WaterLevelReading> findAll();
    List<WaterLevelReading> findBySensorId(Long sensorId);
    WaterLevelReading save(WaterLevelReading reading);
    void deleteById(Long id);
}
