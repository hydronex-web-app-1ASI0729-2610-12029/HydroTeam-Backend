package com.tankiq.refill.domain.model.events;

import com.tankiq.refill.domain.model.aggregates.Refill;
import java.time.Instant;

public record RefillCreatedEvent(
        Long refillId,
        Instant refillDate,
        Double liters,
        Double costSoles,
        String supplierName,
        String invoiceNumber,
        Long buildingId,
        Long registeredByUserId
) {
    public static RefillCreatedEvent from(Refill refill) {
        return new RefillCreatedEvent(
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
