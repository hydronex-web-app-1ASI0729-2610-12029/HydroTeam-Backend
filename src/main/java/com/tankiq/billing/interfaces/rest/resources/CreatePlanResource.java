package com.tankiq.billing.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePlanResource(
        @NotBlank(message = "Plan cannot be blank") @Schema(description = "Plan name") String name,
        @NotNull @Schema(description = "Plan price in soles per month") @Min(value = 1) Double priceSoles,
        @NotBlank(message = "Features cannot be blank") @Schema(description = "Plan feature details") String features,
        @NotNull @Schema(description = "Maximum number of sensors allowed") Integer maxSensors) {
}
