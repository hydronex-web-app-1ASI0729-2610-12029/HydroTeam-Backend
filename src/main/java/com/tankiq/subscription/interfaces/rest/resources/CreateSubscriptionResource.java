package com.tankiq.subscription.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateSubscriptionResource(
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotBlank String status,
        @NotNull Long buildingId,
        @NotNull Long planId
) {
}
