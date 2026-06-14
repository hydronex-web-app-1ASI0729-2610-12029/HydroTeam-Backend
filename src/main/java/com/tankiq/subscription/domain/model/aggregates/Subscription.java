package com.tankiq.subscription.domain.model.aggregates;

import com.tankiq.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.tankiq.subscription.domain.model.events.SubscriptionCreatedEvent;
import com.tankiq.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import java.time.LocalDate;

public class Subscription extends AbstractDomainAggregateRoot<Subscription> {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Long buildingId;
    private Long planId;

    public Subscription(Long id, LocalDate startDate, LocalDate endDate, String status, Long buildingId, Long planId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.buildingId = buildingId;
        this.planId = planId;
    }

    public Subscription(LocalDate startDate, LocalDate endDate, String status, Long buildingId, Long planId) {
        this(null, startDate, endDate, status, buildingId, planId);
    }

    public Subscription(CreateSubscriptionCommand command) {
        this(command.startDate(), command.endDate(), command.status(), command.buildingId(), command.planId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void onCreated() {
        registerDomainEvent(SubscriptionCreatedEvent.from(this));
    }
}
