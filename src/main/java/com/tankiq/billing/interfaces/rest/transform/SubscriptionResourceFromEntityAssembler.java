package com.tankiq.subscription.interfaces.rest.transform;

import com.tankiq.subscription.domain.model.aggregates.Subscription;
import com.tankiq.subscription.interfaces.rest.resources.SubscriptionResource;

public final class SubscriptionResourceFromEntityAssembler {
    private SubscriptionResourceFromEntityAssembler() {
    }

    public static SubscriptionResource toResourceFromEntity(Subscription subscription) {
        return new SubscriptionResource(
                subscription.getId(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getStatus(),
                subscription.getBuildingId(),
                subscription.getPlanId()
        );
    }
}
