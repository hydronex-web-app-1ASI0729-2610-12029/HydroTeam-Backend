package com.tankiq.billing.infrastructure.persistence.jpa.adapters;

import com.tankiq.billing.domain.model.aggregates.Plan;
import com.tankiq.billing.domain.repositories.PlanRepository;
import com.tankiq.billing.infrastructure.persistence.jpa.assemblers.PlanPersistenceAssembler;
import com.tankiq.billing.infrastructure.persistence.jpa.repositories.PlanPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of plan repository
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
@Repository
public class PlanRepositoryImpl implements PlanRepository {
    private final PlanPersistenceRepository planPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public PlanRepositoryImpl(PlanPersistenceRepository planPersistenceRepository,
                              ApplicationEventPublisher eventPublisher) {
        this.planPersistenceRepository = planPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Plan save(Plan plan) {
        boolean isNew = plan.getId() == null;

        var persistenceEntity = PlanPersistenceAssembler.toPersistenceFromDomain(plan);
        var savedEntity = planPersistenceRepository.save(persistenceEntity);
        var savedPlan = PlanPersistenceAssembler.toDomainFromPersistence(savedEntity);

        if (isNew) {
            savedPlan.domainEvents().forEach(eventPublisher::publishEvent);
            savedPlan.clearDomainEvents();
        }

        return savedPlan;
    }

    @Override
    public List<Plan> findAll() {
        return planPersistenceRepository.findAll().stream()
                .map(PlanPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Optional<Plan> findById(Long id) {
        return planPersistenceRepository.findById(id)
                .map(PlanPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Optional<Plan> findByName(String name) {
        return planPersistenceRepository.findByName(name)
                .map(PlanPersistenceAssembler::toDomainFromPersistence);
    }
}
