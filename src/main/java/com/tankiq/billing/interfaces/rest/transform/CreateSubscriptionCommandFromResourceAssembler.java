package com.tankiq.subscription.interfaces.rest.transform;

import com.tankiq.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.tankiq.subscription.interfaces.rest.resources.CreateSubscriptionResource;

public final class CreateSubscriptionCommandFromResourceAssembler {
    private CreateSubscriptionCommandFromResourceAssembler() {
    }

    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand(
                resource.startDate(),
                resource.endDate(),
                resource.status(),
                resource.buildingId(),
                resource.planId()
        );
    }
}
