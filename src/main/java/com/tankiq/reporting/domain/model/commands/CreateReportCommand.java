package com.tankiq.reporting.domain.model.commands;

public record CreateReportCommand(
        Integer periodMonth,
        Integer periodYear,
        Long buildingId,
        Long generatedByUserId
) {
}
