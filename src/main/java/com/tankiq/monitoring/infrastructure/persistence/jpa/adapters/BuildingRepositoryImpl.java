package com.tankiq.monitoring.infrastructure.persistence.jpa.adapters;

import com.tankiq.monitoring.domain.model.aggregates.Building;
import com.tankiq.monitoring.domain.repositories.BuildingRepository;
import com.tankiq.monitoring.infrastructure.persistence.jpa.assemblers.BuildingPersistenceAssembler;
import com.tankiq.monitoring.infrastructure.persistence.jpa.repositories.BuildingPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    private final BuildingPersistenceRepository buildingPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public BuildingRepositoryImpl(BuildingPersistenceRepository buildingPersistenceRepository, ApplicationEventPublisher eventPublisher) {
        this.buildingPersistenceRepository = buildingPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Building> findById(Long id) {
        return buildingPersistenceRepository.findById(id).map(BuildingPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Building> findAll() {
        return buildingPersistenceRepository.findAll().stream().map(BuildingPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public Building save(Building building) {
        boolean isNew = building.getId() == null;
        var savedEntity = buildingPersistenceRepository.save(BuildingPersistenceAssembler.toPersistenceFromDomain(building));
        var savedBuilding = BuildingPersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedBuilding.onCreated();
            savedBuilding.domainEvents().forEach(eventPublisher::publishEvent);
            savedBuilding.clearDomainEvents();
        }
        return savedBuilding;
    }
}
