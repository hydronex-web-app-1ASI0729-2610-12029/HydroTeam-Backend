package com.tankiq.refill.domain.repositories;

import com.tankiq.refill.domain.model.aggregates.Refill;

import java.util.List;
import java.util.Optional;

public interface RefillRepository {
    Optional<Refill> findById(Long id);
    List<Refill> findAll();
    Refill save(Refill refill);
    void deleteById(Long id);
}
