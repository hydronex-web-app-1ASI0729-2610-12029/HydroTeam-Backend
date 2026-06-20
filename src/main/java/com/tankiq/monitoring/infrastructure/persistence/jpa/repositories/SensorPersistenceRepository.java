package com.tankiq.monitoring.infrastructure.persistence.jpa.repositories;

import com.tankiq.monitoring.infrastructure.persistence.jpa.entities.SensorPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorPersistenceRepository extends JpaRepository<SensorPersistenceEntity, Long> {
    List<SensorPersistenceEntity> findByCisternId(Long cisternId);
}
