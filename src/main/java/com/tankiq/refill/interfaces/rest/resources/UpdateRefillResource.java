package com.tankiq.refill.interfaces.rest.resources;

public record UpdateRefillResource(
        String refillDate,
        Double liters,
        Double costSoles,
        String supplierName,
        String invoiceNumber,
        Long buildingId,
        Long registeredByUserId
) {
}
