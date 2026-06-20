package com.tankiq.billing.interfaces.rest.transform;

import com.tankiq.billing.domain.model.aggregates.Subscription;
import com.tankiq.billing.interfaces.rest.resources.SubscriptionResource;

public final class SubscriptionResourceFromEntityAssembler {
    private SubscriptionResourceFromEntityAssembler() {
    }

    public static SubscriptionResource toResourceFromEntity(Subscription subscription) {
        return new SubscriptionResource(
                subscription.getId(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getStatus().name(),
                subscription.getBuildingId(),
                subscription.getPlanId()
        );
    }
}
