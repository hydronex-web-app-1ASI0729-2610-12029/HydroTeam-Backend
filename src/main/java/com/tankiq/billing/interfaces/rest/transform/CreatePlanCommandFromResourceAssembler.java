package com.tankiq.billing.interfaces.rest.transform;

import com.tankiq.billing.domain.model.commands.CreatePlanCommand;
import com.tankiq.billing.interfaces.rest.resources.CreatePlanResource;

public class CreatePlanCommandFromResourceAssembler {
    public static CreatePlanCommand toCommandFromResource(CreatePlanResource resource) {
        return new CreatePlanCommand(
                resource.name(),
                resource.priceSoles(),
                resource.features(),
                resource.maxSensors()
        );
    }
}
