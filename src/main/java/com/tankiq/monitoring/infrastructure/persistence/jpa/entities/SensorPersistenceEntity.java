package com.tankiq.monitoring.infrastructure.persistence.jpa.entities;

import com.tankiq.monitoring.domain.model.enums.SensorStatus;
import com.tankiq.monitoring.domain.model.enums.SensorType;
import com.tankiq.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "sensors")
public class SensorPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "hardware_id", nullable = false, unique = true)
    private String hardwareId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SensorType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SensorStatus status;

    @Column(name = "last_sync_at")
    private Date lastSyncAt;

    @Column(name = "cistern_id", nullable = false)
    private Long cisternId;

    public String getHardwareId() { return hardwareId; }
    public SensorType getType() { return type; }
    public SensorStatus getStatus() { return status; }
    public Date getLastSyncAt() { return lastSyncAt; }
    public Long getCisternId() { return cisternId; }

    public void setHardwareId(String hardwareId) { this.hardwareId = hardwareId; }
    public void setType(SensorType type) { this.type = type; }
    public void setStatus(SensorStatus status) { this.status = status; }
    public void setLastSyncAt(Date lastSyncAt) { this.lastSyncAt = lastSyncAt; }
    public void setCisternId(Long cisternId) { this.cisternId = cisternId; }
}
