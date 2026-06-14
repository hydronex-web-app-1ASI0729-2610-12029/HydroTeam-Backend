package com.tankiq.monitoring.infrastructure.persistence.jpa.repositories;

import com.tankiq.monitoring.infrastructure.persistence.jpa.entities.CisternPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CisternPersistenceRepository extends JpaRepository<CisternPersistenceEntity, Long> {
}
