package com.tankiq.monitoring.infrastructure.persistence.jpa.adapters;

import com.tankiq.monitoring.domain.model.aggregates.Sensor;
import com.tankiq.monitoring.domain.repositories.SensorRepository;
import com.tankiq.monitoring.infrastructure.persistence.jpa.assemblers.SensorPersistenceAssembler;
import com.tankiq.monitoring.infrastructure.persistence.jpa.repositories.SensorPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SensorRepositoryImpl implements SensorRepository {
    private final SensorPersistenceRepository sensorPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public SensorRepositoryImpl(SensorPersistenceRepository sensorPersistenceRepository, ApplicationEventPublisher eventPublisher) {
        this.sensorPersistenceRepository = sensorPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Sensor> findById(Long id) {
        return sensorPersistenceRepository.findById(id).map(SensorPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Sensor> findAll() {
        return sensorPersistenceRepository.findAll().stream().map(SensorPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public List<Sensor> findByCisternId(Long cisternId) {
        return sensorPersistenceRepository.findByCisternId(cisternId).stream().map(SensorPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public Sensor save(Sensor sensor) {
        boolean isNew = sensor.getId() == null;
        var savedEntity = sensorPersistenceRepository.save(SensorPersistenceAssembler.toPersistenceFromDomain(sensor));
        var savedSensor = SensorPersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedSensor.onCreated();
            savedSensor.domainEvents().forEach(eventPublisher::publishEvent);
            savedSensor.clearDomainEvents();
        }
        return savedSensor;
    }
}
