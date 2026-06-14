package com.tankiq.reporting.infrastructure.persistence.jpa.entities;

import com.tankiq.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "reports")
public class ReportPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "period_month")
    private Integer periodMonth;

    @Column(name = "period_year")
    private Integer periodYear;

    @Column(name = "total_cost_soles")
    private Double totalCostSoles;

    @Column(name = "total_water_liters")
    private Double totalWaterLiters;

    @Column(name = "generated_at")
    private Instant generatedAt;

    @Column(name = "building_id")
    private Long buildingId;

    @Column(name = "generated_by_user_id")
    private Long generatedByUserId;

    public Integer getPeriodMonth() {
        return periodMonth;
    }

    public Integer getPeriodYear() {
        return periodYear;
    }

    public Double getTotalCostSoles() {
        return totalCostSoles;
    }

    public Double getTotalWaterLiters() {
        return totalWaterLiters;
    }

    public Instant getGeneratedAt() {
        return generatedAt;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public Long getGeneratedByUserId() {
        return generatedByUserId;
    }

    public void setPeriodMonth(Integer periodMonth) {
        this.periodMonth = periodMonth;
    }

    public void setPeriodYear(Integer periodYear) {
        this.periodYear = periodYear;
    }

    public void setTotalCostSoles(Double totalCostSoles) {
        this.totalCostSoles = totalCostSoles;
    }

    public void setTotalWaterLiters(Double totalWaterLiters) {
        this.totalWaterLiters = totalWaterLiters;
    }

    public void setGeneratedAt(Instant generatedAt) {
        this.generatedAt = generatedAt;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public void setGeneratedByUserId(Long generatedByUserId) {
        this.generatedByUserId = generatedByUserId;
    }

}
