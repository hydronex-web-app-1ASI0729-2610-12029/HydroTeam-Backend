package com.tankiq.monitoring.domain.model.aggregates;

import com.tankiq.monitoring.domain.model.commands.CreateWaterLevelReadingCommand;
import com.tankiq.monitoring.domain.model.events.WaterLevelReadingCreatedEvent;
import com.tankiq.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

import java.util.Date;

public class WaterLevelReading extends AbstractDomainAggregateRoot<WaterLevelReading> {
    private Long id;
    private Double levelPercent;
    private Double volumeLiters;
    private Date recordedAt;
    private Long sensorId;

    public WaterLevelReading(Long id, Double levelPercent, Double volumeLiters, Date recordedAt, Long sensorId) {
        this.id = id;
        this.levelPercent = levelPercent;
        this.volumeLiters = volumeLiters;
        this.recordedAt = recordedAt;
        this.sensorId = sensorId;
    }

    public WaterLevelReading(Double levelPercent, Double volumeLiters, Date recordedAt, Long sensorId) {
        this(null, levelPercent, volumeLiters, recordedAt, sensorId);
    }

    public WaterLevelReading(CreateWaterLevelReadingCommand command) {
        this(command.levelPercent(), command.volumeLiters(), command.recordedAt(), command.sensorId());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getLevelPercent() { return levelPercent; }
    public Double getVolumeLiters() { return volumeLiters; }
    public Date getRecordedAt() { return recordedAt; }
    public Long getSensorId() { return sensorId; }

    public void update(Double levelPercent, Double volumeLiters, Date recordedAt, Long sensorId) {
        this.levelPercent = levelPercent;
        this.volumeLiters = volumeLiters;
        this.recordedAt = recordedAt;
        this.sensorId = sensorId;
    }

    public void onCreated() {
        registerDomainEvent(WaterLevelReadingCreatedEvent.from(this));
    }
}
