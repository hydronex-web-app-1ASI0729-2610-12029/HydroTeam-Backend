package com.tankiq.monitoring.domain.model.aggregates;

import com.tankiq.monitoring.domain.model.commands.CreateCisternCommand;
import com.tankiq.monitoring.domain.model.events.CisternCreatedEvent;
import com.tankiq.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

public class Cistern extends AbstractDomainAggregateRoot<Cistern> {
    private Long id;
    private Double capacityLiters;
    private Double currentLevelPercent;
    private Double alertThresholdPercent;
    private Long buildingId;

    public Cistern(Long id, Double capacityLiters, Double currentLevelPercent, Double alertThresholdPercent, Long buildingId) {
        this.id = id;
        this.capacityLiters = capacityLiters;
        this.currentLevelPercent = currentLevelPercent;
        this.alertThresholdPercent = alertThresholdPercent;
        this.buildingId = buildingId;
    }

    public Cistern(Double capacityLiters, Double currentLevelPercent, Double alertThresholdPercent, Long buildingId) {
        this(null, capacityLiters, currentLevelPercent, alertThresholdPercent, buildingId);
    }

    public Cistern(CreateCisternCommand command) {
        this(command.capacityLiters(), command.currentLevelPercent(), command.alertThresholdPercent(), command.buildingId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCapacityLiters() {
        return capacityLiters;
    }

    public Double getCurrentLevelPercent() {
        return currentLevelPercent;
    }

    public Double getAlertThresholdPercent() {
        return alertThresholdPercent;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void onCreated() {
        registerDomainEvent(CisternCreatedEvent.from(this));
    }
}
