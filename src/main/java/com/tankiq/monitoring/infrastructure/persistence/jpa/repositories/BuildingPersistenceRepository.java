package com.tankiq.monitoring.infrastructure.persistence.jpa.repositories;

import com.tankiq.monitoring.infrastructure.persistence.jpa.entities.BuildingPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingPersistenceRepository extends JpaRepository<BuildingPersistenceEntity, Long> {
}
