package com.tankiq.billing.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

public record PlanResource(
        @Schema(description = "Plan id", example = "1") Long id,
        @Schema(description = "Plan name", example = "Premium") String name,
        @Schema(description = "Plan price in soles per month", example = "149.90") Double priceSoles,
        @Schema(description = "Plan feature details", example = "24/7 support & 2 iot enabled") String features,
        @Schema(description = "Maximum number of sensors allowed", example = "05") Integer maxSensors
) {
}
