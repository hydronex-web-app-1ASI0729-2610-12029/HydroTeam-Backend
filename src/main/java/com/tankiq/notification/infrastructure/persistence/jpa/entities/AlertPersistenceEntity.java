package com.tankiq.notification.infrastructure.persistence.jpa.entities;

import com.tankiq.notification.domain.model.valueobjects.AlertStatus;
import com.tankiq.notification.domain.model.valueobjects.AlertType;
import com.tankiq.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "alerts")
public class AlertPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AlertType type;

    @Column(name = "message")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AlertStatus status;

    @Column(name = "triggered_at")
    private Instant triggeredAt;

    @Column(name = "resolved_at")
    private Instant resolvedAt;

    @Column(name = "cistern_id")
    private Long cisternId;

    public AlertType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public Instant getTriggeredAt() {
        return triggeredAt;
    }

    public Instant getResolvedAt() {
        return resolvedAt;
    }

    public Long getCisternId() {
        return cisternId;
    }

    public void setType(AlertType type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(AlertStatus status) {
        this.status = status;
    }

    public void setTriggeredAt(Instant triggeredAt) {
        this.triggeredAt = triggeredAt;
    }

    public void setResolvedAt(Instant resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public void setCisternId(Long cisternId) {
        this.cisternId = cisternId;
    }
}