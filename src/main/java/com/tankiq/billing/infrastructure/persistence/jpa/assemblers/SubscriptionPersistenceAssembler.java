package com.tankiq.billing.infrastructure.persistence.jpa.assemblers;

import com.tankiq.billing.domain.model.aggregates.Subscription;
import com.tankiq.billing.infrastructure.persistence.jpa.entities.SubscriptionPersistenceEntity;

/**
 * Subscription Assembler
 * <description>
 *     This is the assembler responsible for transforming the database entity into a resource, and vice versa.
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
public final class SubscriptionPersistenceAssembler {
    public static Subscription toDomainFromPersistence(SubscriptionPersistenceEntity entity) {
        if (entity == null) return null;

        return new Subscription(
                entity.getId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getStatus(),
                entity.getBuildingId(),
                entity.getPlanId()
        );
    }

    public static SubscriptionPersistenceEntity toPersistenceFromDomain(Subscription subscription){
        if (subscription == null) return null;

        SubscriptionPersistenceEntity entity = new SubscriptionPersistenceEntity();

        entity.setId(subscription.getId());
        entity.setStartDate(subscription.getStartDate());
        entity.setEndDate(subscription.getEndDate());
        entity.setStatus(subscription.getStatus());
        entity.setBuildingId(subscription.getBuildingId());
        entity.setPlanId(subscription.getPlanId());

        return entity;
    }
}