package com.tankiq.refill.interfaces.rest.resources;

import java.time.Instant;
public record RefillResource(
        Long id,
        Instant refillDate,
        Double liters,
        Double costSoles,
        String supplierName,
        String invoiceNumber,
        Long buildingId,
        Long registeredByUserId
) {
}
