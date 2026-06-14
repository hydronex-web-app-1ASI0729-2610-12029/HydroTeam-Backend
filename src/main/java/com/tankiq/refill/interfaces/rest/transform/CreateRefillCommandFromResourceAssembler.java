package com.tankiq.refill.interfaces.rest.transform;

import com.tankiq.refill.domain.model.commands.CreateRefillCommand;
import com.tankiq.refill.interfaces.rest.resources.CreateRefillResource;

public final class CreateRefillCommandFromResourceAssembler {
    private CreateRefillCommandFromResourceAssembler() {
    }

    public static CreateRefillCommand toCommandFromResource(CreateRefillResource resource) {
        return new CreateRefillCommand(
                resource.refillDate(),
                resource.liters(),
                resource.costSoles(),
                resource.supplierName(),
                resource.invoiceNumber(),
                resource.buildingId(),
                resource.registeredByUserId()
        );
    }
}
