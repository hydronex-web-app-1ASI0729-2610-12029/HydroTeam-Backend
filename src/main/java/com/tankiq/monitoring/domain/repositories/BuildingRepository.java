package com.tankiq.monitoring.domain.repositories;

import com.tankiq.monitoring.domain.model.aggregates.Building;

import java.util.List;
import java.util.Optional;

public interface BuildingRepository {
    Optional<Building> findById(Long id);
    List<Building> findAll();
    Building save(Building building);
}
