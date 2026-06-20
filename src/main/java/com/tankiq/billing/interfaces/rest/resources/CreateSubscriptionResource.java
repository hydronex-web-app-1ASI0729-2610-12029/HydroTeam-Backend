package com.tankiq.billing.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateSubscriptionResource(
        @NotNull (message = "{validation.not-null}") @Schema(description = "Subscription start date", example = "14-07-2005") LocalDate startDate,
        @NotNull (message = "{validation.not-null}") @Schema(description = "Subscription end date", example = "14-07-2005") LocalDate endDate,
        @NotNull (message = "{validation.not-null}") @Schema(description = "Subscription current status", example = "ACTIVE") String status,
        @NotNull (message = "{validation.not-null}") @Schema(description = "Subscription building id", example = "2") Long buildingId,
        @NotNull (message = "{validation.not-null}") @Schema(description = "Subscription plan id", example = "3") Long planId
) {
}
