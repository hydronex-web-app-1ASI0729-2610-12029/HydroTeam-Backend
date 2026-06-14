package com.tankiq.refill.interfaces.rest.transform;

import com.tankiq.refill.domain.model.aggregates.Refill;
import com.tankiq.refill.interfaces.rest.resources.RefillResource;

public final class RefillResourceFromEntityAssembler {
    private RefillResourceFromEntityAssembler() {
    }

    public static RefillResource toResourceFromEntity(Refill refill) {
        return new RefillResource(
                refill.getId(),
                refill.getRefillDate(),
                refill.getLiters(),
                refill.getCostSoles(),
                refill.getSupplierName(),
                refill.getInvoiceNumber(),
                refill.getBuildingId(),
                refill.getRegisteredByUserId()
        );
    }
}
