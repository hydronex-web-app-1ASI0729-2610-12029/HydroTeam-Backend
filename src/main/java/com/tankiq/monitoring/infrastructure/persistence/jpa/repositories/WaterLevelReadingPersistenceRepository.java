package com.tankiq.monitoring.infrastructure.persistence.jpa.repositories;

import com.tankiq.monitoring.infrastructure.persistence.jpa.entities.WaterLevelReadingPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaterLevelReadingPersistenceRepository extends JpaRepository<WaterLevelReadingPersistenceEntity, Long> {
    List<WaterLevelReadingPersistenceEntity> findBySensorId(Long sensorId);
}
