package com.tankiq.reporting.interfaces.rest.resources;

import java.time.Instant;
public record ReportResource(
        Long id,
        Integer periodMonth,
        Integer periodYear,
        Double totalCostSoles,
        Double totalWaterLiters,
        Instant generatedAt,
        Long buildingId,
        Long generatedByUserId
) {
}
