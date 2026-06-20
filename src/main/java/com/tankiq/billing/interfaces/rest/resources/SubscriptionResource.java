package com.tankiq.billing.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
public record SubscriptionResource(
        @Schema(description = "Subscription id", example = "1") Long id,
        @Schema(description = "Subscription start date", example = "2005-07-14") LocalDate startDate,
        @Schema(description = "Subscription end date", example = "2005-07-14") LocalDate endDate,
        @Schema(description = "Subscription current status", example = "ACTIVE") String status,
        @Schema(description = "Subscription building id", example = "2") Long buildingId,
        @Schema(description = "Subscription plan id", example = "3") Long planId
) {
}
