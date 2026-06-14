package com.tankiq.refill.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record CreateRefillResource(
        @NotNull Instant refillDate,
        @NotNull Double liters,
        @NotNull Double costSoles,
        @NotBlank String supplierName,
        @NotBlank String invoiceNumber,
        @NotNull Long buildingId,
        @NotNull Long registeredByUserId
) {
}
