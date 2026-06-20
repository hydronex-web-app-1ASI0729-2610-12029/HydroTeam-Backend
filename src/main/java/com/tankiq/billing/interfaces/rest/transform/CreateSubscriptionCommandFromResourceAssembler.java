package com.tankiq.billing.interfaces.rest.transform;

import com.tankiq.billing.domain.model.commands.CreateSubscriptionCommand;
import com.tankiq.billing.interfaces.rest.resources.CreateSubscriptionResource;

public final class CreateSubscriptionCommandFromResourceAssembler {
    private CreateSubscriptionCommandFromResourceAssembler() {
    }

    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand(
                null,
                resource.startDate(),
                resource.endDate(),
                resource.status(),
                resource.buildingId(),
                resource.planId()
        );
    }
}
