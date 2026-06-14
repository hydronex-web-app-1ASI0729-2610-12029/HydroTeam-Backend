package com.tankiq.monitoring.domain.repositories;

import com.tankiq.monitoring.domain.model.aggregates.Cistern;

import java.util.List;
import java.util.Optional;

public interface CisternRepository {
    Optional<Cistern> findById(Long id);
    List<Cistern> findAll();
    Cistern save(Cistern cistern);
}
