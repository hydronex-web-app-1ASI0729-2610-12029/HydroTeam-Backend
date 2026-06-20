package com.tankiq.billing.infrastructure.persistence.jpa.adapters;

import com.tankiq.billing.domain.model.aggregates.Subscription;
import com.tankiq.billing.domain.repositories.SubscriptionRepository;
import com.tankiq.billing.infrastructure.persistence.jpa.assemblers.SubscriptionPersistenceAssembler;
import com.tankiq.billing.infrastructure.persistence.jpa.repositories.SubscriptionPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of subscription repository
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {
    private final SubscriptionPersistenceRepository subscriptionPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public SubscriptionRepositoryImpl(SubscriptionPersistenceRepository subscriptionPersistenceRepository,
                                      ApplicationEventPublisher eventPublisher) {
        this.subscriptionPersistenceRepository = subscriptionPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Subscription save(Subscription subscription) {
        boolean isNew = subscription.getId() == null;

        var persistenceEntity = SubscriptionPersistenceAssembler.toPersistenceFromDomain(subscription);
        var savedEntity = subscriptionPersistenceRepository.save(persistenceEntity);
        var savedSubscription = SubscriptionPersistenceAssembler.toDomainFromPersistence(savedEntity);

        if (isNew) {
            savedSubscription.domainEvents().forEach(eventPublisher::publishEvent);
            savedSubscription.clearDomainEvents();
        }

        return savedSubscription;
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptionPersistenceRepository.findAll().stream()
                .map(SubscriptionPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Optional<Subscription> findById(Long id) {
        return subscriptionPersistenceRepository.findById(id)
                .map(SubscriptionPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Optional<Subscription> findByBuildingId(Long buildingId) {
        return subscriptionPersistenceRepository.findByBuildingId(buildingId)
                .map(SubscriptionPersistenceAssembler::toDomainFromPersistence);
    }
}
