package com.tankiq.monitoring.infrastructure.persistence.jpa.adapters;

import com.tankiq.monitoring.domain.model.aggregates.WaterLevelReading;
import com.tankiq.monitoring.domain.repositories.WaterLevelReadingRepository;
import com.tankiq.monitoring.infrastructure.persistence.jpa.assemblers.WaterLevelReadingPersistenceAssembler;
import com.tankiq.monitoring.infrastructure.persistence.jpa.repositories.WaterLevelReadingPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WaterLevelReadingRepositoryImpl implements WaterLevelReadingRepository {
    private final WaterLevelReadingPersistenceRepository waterLevelReadingPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public WaterLevelReadingRepositoryImpl(WaterLevelReadingPersistenceRepository waterLevelReadingPersistenceRepository, ApplicationEventPublisher eventPublisher) {
        this.waterLevelReadingPersistenceRepository = waterLevelReadingPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<WaterLevelReading> findById(Long id) {
        return waterLevelReadingPersistenceRepository.findById(id).map(WaterLevelReadingPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<WaterLevelReading> findAll() {
        return waterLevelReadingPersistenceRepository.findAll().stream().map(WaterLevelReadingPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public List<WaterLevelReading> findBySensorId(Long sensorId) {
        return waterLevelReadingPersistenceRepository.findBySensorId(sensorId).stream().map(WaterLevelReadingPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public WaterLevelReading save(WaterLevelReading reading) {
        boolean isNew = reading.getId() == null;
        var savedEntity = waterLevelReadingPersistenceRepository.save(WaterLevelReadingPersistenceAssembler.toPersistenceFromDomain(reading));
        var savedReading = WaterLevelReadingPersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedReading.onCreated();
            savedReading.domainEvents().forEach(eventPublisher::publishEvent);
            savedReading.clearDomainEvents();
        }
        return savedReading;
    }
}
