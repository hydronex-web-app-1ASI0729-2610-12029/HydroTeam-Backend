package com.tankiq.notification.domain.model.aggregates;

import com.tankiq.notification.domain.model.commands.CreateAlertCommand;
import com.tankiq.notification.domain.model.events.AlertCreatedEvent;
import com.tankiq.notification.domain.model.valueobjects.AlertStatus;
import com.tankiq.notification.domain.model.valueobjects.AlertType;
import com.tankiq.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import java.time.Instant;

public class Alert extends AbstractDomainAggregateRoot<Alert> {
    private Long id;
    private AlertType type;
    private String message;
    private AlertStatus status;
    private Instant triggeredAt;
    private Instant resolvedAt;
    private Long cisternId;

    public Alert(Long id, AlertType type, String message, AlertStatus status, Instant triggeredAt, Instant resolvedAt, Long cisternId) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.status = status;
        this.triggeredAt = triggeredAt;
        this.resolvedAt = resolvedAt;
        this.cisternId = cisternId;
    }

    public Alert(AlertType type, String message, AlertStatus status, Instant triggeredAt, Instant resolvedAt, Long cisternId) {
        this(null, type, message, status, triggeredAt, resolvedAt, cisternId);
    }

    public Alert(CreateAlertCommand command) {
        this(command.type(), command.message(), command.status(), command.triggeredAt(), command.resolvedAt(), command.cisternId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void onCreated() {
        registerDomainEvent(AlertCreatedEvent.from(this));
    }
}