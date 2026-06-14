package com.tankiq.subscription.infrastructure.persistence.jpa.adapters;

import com.tankiq.subscription.domain.model.aggregates.Subscription;
import com.tankiq.subscription.domain.repositories.SubscriptionRepository;
import com.tankiq.subscription.infrastructure.persistence.jpa.assemblers.SubscriptionPersistenceAssembler;
import com.tankiq.subscription.infrastructure.persistence.jpa.repositories.SubscriptionPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {
    private final SubscriptionPersistenceRepository subscriptionPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public SubscriptionRepositoryImpl(SubscriptionPersistenceRepository subscriptionPersistenceRepository, ApplicationEventPublisher eventPublisher) {
        this.subscriptionPersistenceRepository = subscriptionPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Subscription> findById(Long id) {
        return subscriptionPersistenceRepository.findById(id).map(SubscriptionPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptionPersistenceRepository.findAll().stream().map(SubscriptionPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public Subscription save(Subscription subscription) {
        boolean isNew = subscription.getId() == null;
        var savedEntity = subscriptionPersistenceRepository.save(SubscriptionPersistenceAssembler.toPersistenceFromDomain(subscription));
        var savedSubscription = SubscriptionPersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedSubscription.onCreated();
            savedSubscription.domainEvents().forEach(eventPublisher::publishEvent);
            savedSubscription.clearDomainEvents();
        }
        return savedSubscription;
    }
}
