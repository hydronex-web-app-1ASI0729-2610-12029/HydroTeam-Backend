package com.tankiq.refill.infrastructure.persistence.jpa.repositories;

import com.tankiq.refill.infrastructure.persistence.jpa.entities.RefillPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface RefillPersistenceRepository extends JpaRepository<RefillPersistenceEntity, Long> {
    List<RefillPersistenceEntity> findAllByBuildingIdAndRefillDateGreaterThanEqualAndRefillDateLessThan(
            Long buildingId, Instant startDate, Instant endDate);
}
