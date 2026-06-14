package com.tankiq.subscription.domain.model.events;

import com.tankiq.subscription.domain.model.aggregates.Subscription;
import java.time.LocalDate;

public record SubscriptionCreatedEvent(
        Long subscriptionId,
        LocalDate startDate,
        LocalDate endDate,
        String status,
        Long buildingId,
        Long planId
) {
    public static SubscriptionCreatedEvent from(Subscription subscription) {
        return new SubscriptionCreatedEvent(
                subscription.getId(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getStatus(),
                subscription.getBuildingId(),
                subscription.getPlanId()
        );
    }
}
