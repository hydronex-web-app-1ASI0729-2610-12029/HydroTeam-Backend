package com.tankiq.monitoring.domain.repositories;

import com.tankiq.monitoring.domain.model.aggregates.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorRepository {
    Optional<Sensor> findById(Long id);
    List<Sensor> findAll();
    List<Sensor> findByCisternId(Long cisternId);
    Sensor save(Sensor sensor);
}
