package com.tankiq.monitoring.infrastructure.persistence.jpa.adapters;

import com.tankiq.monitoring.domain.model.aggregates.Cistern;
import com.tankiq.monitoring.domain.repositories.CisternRepository;
import com.tankiq.monitoring.infrastructure.persistence.jpa.assemblers.CisternPersistenceAssembler;
import com.tankiq.monitoring.infrastructure.persistence.jpa.repositories.CisternPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CisternRepositoryImpl implements CisternRepository {
    private final CisternPersistenceRepository cisternPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CisternRepositoryImpl(CisternPersistenceRepository cisternPersistenceRepository, ApplicationEventPublisher eventPublisher) {
        this.cisternPersistenceRepository = cisternPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Cistern> findById(Long id) {
        return cisternPersistenceRepository.findById(id).map(CisternPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Cistern> findAll() {
        return cisternPersistenceRepository.findAll().stream().map(CisternPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public Cistern save(Cistern cistern) {
        boolean isNew = cistern.getId() == null;
        var savedEntity = cisternPersistenceRepository.save(CisternPersistenceAssembler.toPersistenceFromDomain(cistern));
        var savedCistern = CisternPersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedCistern.onCreated();
            savedCistern.domainEvents().forEach(eventPublisher::publishEvent);
            savedCistern.clearDomainEvents();
        }
        return savedCistern;
    }

    @Override
    public void deleteById(Long id) {
        cisternPersistenceRepository.deleteById(id);
    }
}
