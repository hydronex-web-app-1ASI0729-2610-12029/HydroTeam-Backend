package com.tankiq.refill.domain.model.commands;

import java.time.Instant;
public record CreateRefillCommand(
        Instant refillDate,
        Double liters,
        Double costSoles,
        String supplierName,
        String invoiceNumber,
        Long buildingId,
        Long registeredByUserId
) {
}
