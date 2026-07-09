package com.tankiq.reporting.interfaces.rest.resources;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateReportResource(
        @NotNull @Min(1) @Max(12) Integer periodMonth,
        @NotNull @Min(2000) Integer periodYear,
        @NotNull @Positive Long buildingId,
        @NotNull @Positive Long generatedByUserId
) {
}
