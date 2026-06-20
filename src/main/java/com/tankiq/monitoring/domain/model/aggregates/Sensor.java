package com.tankiq.monitoring.domain.model.aggregates;

import com.tankiq.monitoring.domain.model.commands.CreateSensorCommand;
import com.tankiq.monitoring.domain.model.enums.SensorStatus;
import com.tankiq.monitoring.domain.model.enums.SensorType;
import com.tankiq.monitoring.domain.model.events.SensorCreatedEvent;
import com.tankiq.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

import java.util.Date;

public class Sensor extends AbstractDomainAggregateRoot<Sensor> {
    private Long id;
    private String hardwareId;
    private SensorType type;
    private SensorStatus status;
    private Date lastSyncAt;
    private Long cisternId;

    public Sensor(Long id, String hardwareId, SensorType type, SensorStatus status, Date lastSyncAt, Long cisternId) {
        this.id = id;
        this.hardwareId = hardwareId;
        this.type = type;
        this.status = status;
        this.lastSyncAt = lastSyncAt;
        this.cisternId = cisternId;
    }

    public Sensor(String hardwareId, SensorType type, SensorStatus status, Date lastSyncAt, Long cisternId) {
        this(null, hardwareId, type, status, lastSyncAt, cisternId);
    }

    public Sensor(CreateSensorCommand command) {
        this(command.hardwareId(), command.type(), command.status(), command.lastSyncAt(), command.cisternId());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getHardwareId() { return hardwareId; }
    public SensorType getType() { return type; }
    public SensorStatus getStatus() { return status; }
    public Date getLastSyncAt() { return lastSyncAt; }
    public Long getCisternId() { return cisternId; }

    public void onCreated() {
        registerDomainEvent(SensorCreatedEvent.from(this));
    }
}
