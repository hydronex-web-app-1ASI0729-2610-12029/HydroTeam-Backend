package com.tankiq.refill.infrastructure.persistence.jpa.assemblers;

import com.tankiq.refill.domain.model.commands.UpdateRefillCommand;
import com.tankiq.refill.interfaces.rest.resources.UpdateRefillResource;

import java.time.Instant;

public class UpdateRefillCommandFromResourceAssembler {

    public static UpdateRefillCommand toCommandFromResource(
            Long refillId,
            UpdateRefillResource resource) {

        return new UpdateRefillCommand(
                refillId,
                Instant.parse(resource.refillDate()),
                resource.liters(),
                resource.costSoles(),
                resource.supplierName(),
                resource.invoiceNumber(),
                resource.buildingId(),
                resource.registeredByUserId()
        );
    }
}