package com.tankiq.monitoring.infrastructure.persistence.jpa.entities;

import com.tankiq.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "water_level_readings")
public class WaterLevelReadingPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "level_percent", nullable = false)
    private Double levelPercent;

    @Column(name = "volume_liters", nullable = false)
    private Double volumeLiters;

    @Column(name = "recorded_at", nullable = false)
    private Date recordedAt;

    @Column(name = "sensor_id", nullable = false)
    private Long sensorId;

    public Double getLevelPercent() { return levelPercent; }
    public Double getVolumeLiters() { return volumeLiters; }
    public Date getRecordedAt() { return recordedAt; }
    public Long getSensorId() { return sensorId; }

    public void setLevelPercent(Double levelPercent) { this.levelPercent = levelPercent; }
    public void setVolumeLiters(Double volumeLiters) { this.volumeLiters = volumeLiters; }
    public void setRecordedAt(Date recordedAt) { this.recordedAt = recordedAt; }
    public void setSensorId(Long sensorId) { this.sensorId = sensorId; }
}
