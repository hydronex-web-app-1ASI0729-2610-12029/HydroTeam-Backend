package com.tankiq.reporting.interfaces.rest.transform;

import com.tankiq.reporting.domain.model.aggregates.Report;
import com.tankiq.reporting.interfaces.rest.resources.ReportResource;

public final class ReportResourceFromEntityAssembler {
    private ReportResourceFromEntityAssembler() {
    }

    public static ReportResource toResourceFromEntity(Report report) {
        return new ReportResource(
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
