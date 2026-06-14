package com.tankiq.subscription.infrastructure.persistence.jpa.assemblers;

import com.tankiq.subscription.domain.model.aggregates.Subscription;
import com.tankiq.subscription.infrastructure.persistence.jpa.entities.SubscriptionPersistenceEntity;

public final class SubscriptionPersistenceAssembler {
    private SubscriptionPersistenceAssembler() {
    }

    public static Subscription toDomainFromPersistence(SubscriptionPersistenceEntity entity) {
        return new Subscription(
                entity.getId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getStatus(),
                entity.getBuildingId(),
                entity.getPlanId()
        );
    }

    public static SubscriptionPersistenceEntity toPersistenceFromDomain(Subscription subscription) {
        var entity = new SubscriptionPersistenceEntity();
        entity.setId(subscription.getId());
        entity.setStartDate(subscription.getStartDate());
        entity.setEndDate(subscription.getEndDate());
        entity.setStatus(subscription.getStatus());
        entity.setBuildingId(subscription.getBuildingId());
        entity.setPlanId(subscription.getPlanId());
        return entity;
    }
}
