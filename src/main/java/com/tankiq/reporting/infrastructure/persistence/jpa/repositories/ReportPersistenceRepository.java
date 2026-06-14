package com.tankiq.reporting.infrastructure.persistence.jpa.repositories;

import com.tankiq.reporting.infrastructure.persistence.jpa.entities.ReportPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportPersistenceRepository extends JpaRepository<ReportPersistenceEntity, Long> {
}
