package com.tankiq.reporting.domain.model.commands;

import java.time.Instant;
public record CreateReportCommand(
        Integer periodMonth,
        Integer periodYear,
        Double totalCostSoles,
        Double totalWaterLiters,
        Instant generatedAt,
        Long buildingId,
        Long generatedByUserId
) {
}
