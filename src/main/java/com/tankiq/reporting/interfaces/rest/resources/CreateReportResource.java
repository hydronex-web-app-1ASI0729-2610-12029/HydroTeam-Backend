package com.tankiq.reporting.interfaces.rest.resources;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.Instant;

public record CreateReportResource(
        @NotNull @Min(1) @Max(12) Integer periodMonth,
        @NotNull @Min(2000) Integer periodYear,
        @NotNull @PositiveOrZero Double totalCostSoles,
        @NotNull @PositiveOrZero Double totalWaterLiters,
        @NotNull Instant generatedAt,
        @NotNull @Positive Long buildingId,
        @NotNull @Positive Long generatedByUserId
) {
}
