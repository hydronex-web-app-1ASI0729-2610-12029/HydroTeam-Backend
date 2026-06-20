package com.tankiq.billing.interfaces.events;

import com.tankiq.billing.domain.model.aggregates.Subscription;

import java.time.LocalDateTime;

public record SubscriptionActivatedIntegrationEvent(Long subscriptionId, Long buildingId,
                                                    LocalDateTime occurredOn) {
    public static SubscriptionActivatedIntegrationEvent from(Subscription subscription) {
        return new SubscriptionActivatedIntegrationEvent(
                subscription.getId(),
                subscription.getBuildingId(),
                LocalDateTime.now()
        );
    }
}
