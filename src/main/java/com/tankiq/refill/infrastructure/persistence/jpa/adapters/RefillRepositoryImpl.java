package com.tankiq.refill.infrastructure.persistence.jpa.adapters;

import com.tankiq.refill.domain.model.aggregates.Refill;
import com.tankiq.refill.domain.repositories.RefillRepository;
import com.tankiq.refill.infrastructure.persistence.jpa.assemblers.RefillPersistenceAssembler;
import com.tankiq.refill.infrastructure.persistence.jpa.repositories.RefillPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public class RefillRepositoryImpl implements RefillRepository {
    private final RefillPersistenceRepository refillPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public RefillRepositoryImpl(RefillPersistenceRepository refillPersistenceRepository, ApplicationEventPublisher eventPublisher) {
        this.refillPersistenceRepository = refillPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Refill> findById(Long id) {
        return refillPersistenceRepository.findById(id).map(RefillPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Refill> findAll() {
        return refillPersistenceRepository.findAll().stream().map(RefillPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public List<Refill> findByBuildingIdAndRefillDateBetween(Long buildingId, Instant startDate, Instant endDate) {
        return refillPersistenceRepository
                .findAllByBuildingIdAndRefillDateGreaterThanEqualAndRefillDateLessThan(buildingId, startDate, endDate)
                .stream()
                .map(RefillPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Refill save(Refill refill) {
        boolean isNew = refill.getId() == null;
        var savedEntity = refillPersistenceRepository.save(RefillPersistenceAssembler.toPersistenceFromDomain(refill));
        var savedRefill = RefillPersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedRefill.onCreated();
            savedRefill.domainEvents().forEach(eventPublisher::publishEvent);
            savedRefill.clearDomainEvents();
        }
        return savedRefill;
    }

    @Override
    public void deleteById(Long id) {
        refillPersistenceRepository.deleteById(id);
    }
}
