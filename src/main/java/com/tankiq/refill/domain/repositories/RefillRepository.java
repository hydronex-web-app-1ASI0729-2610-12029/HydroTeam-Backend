package com.tankiq.refill.domain.repositories;

import com.tankiq.refill.domain.model.aggregates.Refill;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface RefillRepository {
    Optional<Refill> findById(Long id);
    List<Refill> findAll();
    List<Refill> findByBuildingIdAndRefillDateBetween(Long buildingId, Instant startDate, Instant endDate);
    Refill save(Refill refill);
    void deleteById(Long id);
}
