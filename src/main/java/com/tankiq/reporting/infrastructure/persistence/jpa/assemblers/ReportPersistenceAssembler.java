package com.tankiq.reporting.infrastructure.persistence.jpa.assemblers;

import com.tankiq.reporting.domain.model.aggregates.Report;
import com.tankiq.reporting.infrastructure.persistence.jpa.entities.ReportPersistenceEntity;

public final class ReportPersistenceAssembler {
    private ReportPersistenceAssembler() {
    }

    public static Report toDomainFromPersistence(ReportPersistenceEntity entity) {
        return new Report(
                entity.getId(),
                entity.getPeriodMonth(),
                entity.getPeriodYear(),
                entity.getTotalCostSoles(),
                entity.getTotalWaterLiters(),
                entity.getGeneratedAt(),
                entity.getBuildingId(),
                entity.getGeneratedByUserId()
        );
    }

    public static ReportPersistenceEntity toPersistenceFromDomain(Report report) {
        var entity = new ReportPersistenceEntity();
        entity.setId(report.getId());
        entity.setPeriodMonth(report.getPeriodMonth());
        entity.setPeriodYear(report.getPeriodYear());
        entity.setTotalCostSoles(report.getTotalCostSoles());
        entity.setTotalWaterLiters(report.getTotalWaterLiters());
        entity.setGeneratedAt(report.getGeneratedAt());
        entity.setBuildingId(report.getBuildingId());
        entity.setGeneratedByUserId(report.getGeneratedByUserId());
        return entity;
    }
}
