package com.tankiq.reporting.domain.model.aggregates;

import com.tankiq.reporting.domain.model.commands.CreateReportCommand;
import com.tankiq.reporting.domain.model.events.ReportCreatedEvent;
import com.tankiq.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import java.time.Instant;

public class Report extends AbstractDomainAggregateRoot<Report> {
    private Long id;
    private Integer periodMonth;
    private Integer periodYear;
    private Double totalCostSoles;
    private Double totalWaterLiters;
    private Instant generatedAt;
    private Long buildingId;
    private Long generatedByUserId;

    public Report(Long id, Integer periodMonth, Integer periodYear, Double totalCostSoles, Double totalWaterLiters, Instant generatedAt, Long buildingId, Long generatedByUserId) {
        this.id = id;
        this.periodMonth = periodMonth;
        this.periodYear = periodYear;
        this.totalCostSoles = totalCostSoles;
        this.totalWaterLiters = totalWaterLiters;
        this.generatedAt = generatedAt;
        this.buildingId = buildingId;
        this.generatedByUserId = generatedByUserId;
    }

    public Report(Integer periodMonth, Integer periodYear, Double totalCostSoles, Double totalWaterLiters, Instant generatedAt, Long buildingId, Long generatedByUserId) {
        this(null, periodMonth, periodYear, totalCostSoles, totalWaterLiters, generatedAt, buildingId, generatedByUserId);
    }

    public Report(CreateReportCommand command) {
        this(command.periodMonth(), command.periodYear(), command.totalCostSoles(), command.totalWaterLiters(), command.generatedAt(), command.buildingId(), command.generatedByUserId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void onCreated() {
        registerDomainEvent(ReportCreatedEvent.from(this));
    }
}
