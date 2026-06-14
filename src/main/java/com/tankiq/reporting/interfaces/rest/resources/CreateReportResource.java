package com.tankiq.reporting.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record CreateReportResource(
        @NotNull Integer periodMonth,
        @NotNull Integer periodYear,
        @NotNull Double totalCostSoles,
        @NotNull Double totalWaterLiters,
        @NotNull Instant generatedAt,
        @NotNull Long buildingId,
        @NotNull Long generatedByUserId
) {
}
