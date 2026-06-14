package com.tankiq.reporting.domain.model.events;

import com.tankiq.reporting.domain.model.aggregates.Report;
import java.time.Instant;

public record ReportCreatedEvent(
        Long reportId,
        Integer periodMonth,
        Integer periodYear,
        Double totalCostSoles,
        Double totalWaterLiters,
        Instant generatedAt,
        Long buildingId,
        Long generatedByUserId
) {
    public static ReportCreatedEvent from(Report report) {
        return new ReportCreatedEvent(
                report.getId(),
                report.getPeriodMonth(),
                report.getPeriodYear(),
                report.getTotalCostSoles(),
                report.getTotalWaterLiters(),
                report.getGeneratedAt(),
                report.getBuildingId(),
                report.getGeneratedByUserId()
        );
    }
}
