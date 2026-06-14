package com.tankiq.notification.infrastructure.persistence.jpa.adapters;

import com.tankiq.notification.domain.model.aggregates.Alert;
import com.tankiq.notification.domain.repositories.AlertRepository;
import com.tankiq.notification.infrastructure.persistence.jpa.assemblers.AlertPersistenceAssembler;
import com.tankiq.notification.infrastructure.persistence.jpa.repositories.AlertPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlertRepositoryImpl implements AlertRepository {
    private final AlertPersistenceRepository alertPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public AlertRepositoryImpl(AlertPersistenceRepository alertPersistenceRepository, ApplicationEventPublisher eventPublisher) {
        this.alertPersistenceRepository = alertPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Alert> findById(Long id) {
        return alertPersistenceRepository.findById(id).map(AlertPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Alert> findAll() {
        return alertPersistenceRepository.findAll().stream().map(AlertPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public Alert save(Alert alert) {
        boolean isNew = alert.getId() == null;
        var savedEntity = alertPersistenceRepository.save(AlertPersistenceAssembler.toPersistenceFromDomain(alert));
        var savedAlert = AlertPersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedAlert.onCreated();
            savedAlert.domainEvents().forEach(eventPublisher::publishEvent);
            savedAlert.clearDomainEvents();
        }
        return savedAlert;
    }
}
