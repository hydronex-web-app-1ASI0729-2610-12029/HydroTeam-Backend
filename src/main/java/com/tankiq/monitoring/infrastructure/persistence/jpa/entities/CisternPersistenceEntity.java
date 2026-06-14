package com.tankiq.monitoring.infrastructure.persistence.jpa.entities;

import com.tankiq.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cisterns")
public class CisternPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "capacity_liters")
    private Double capacityLiters;

    @Column(name = "current_level_percent")
    private Double currentLevelPercent;

    @Column(name = "alert_threshold_percent")
    private Double alertThresholdPercent;

    @Column(name = "building_id")
    private Long buildingId;

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

    public void setCapacityLiters(Double capacityLiters) {
        this.capacityLiters = capacityLiters;
    }

    public void setCurrentLevelPercent(Double currentLevelPercent) {
        this.currentLevelPercent = currentLevelPercent;
    }

    public void setAlertThresholdPercent(Double alertThresholdPercent) {
        this.alertThresholdPercent = alertThresholdPercent;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

}
