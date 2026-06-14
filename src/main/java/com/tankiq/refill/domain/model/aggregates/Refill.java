package com.tankiq.refill.domain.model.aggregates;

import com.tankiq.refill.domain.model.commands.CreateRefillCommand;
import com.tankiq.refill.domain.model.events.RefillCreatedEvent;
import com.tankiq.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import java.time.Instant;

public class Refill extends AbstractDomainAggregateRoot<Refill> {
    private Long id;
    private Instant refillDate;
    private Double liters;
    private Double costSoles;
    private String supplierName;
    private String invoiceNumber;
    private Long buildingId;
    private Long registeredByUserId;

    public Refill(Long id, Instant refillDate, Double liters, Double costSoles, String supplierName, String invoiceNumber, Long buildingId, Long registeredByUserId) {
        this.id = id;
        this.refillDate = refillDate;
        this.liters = liters;
        this.costSoles = costSoles;
        this.supplierName = supplierName;
        this.invoiceNumber = invoiceNumber;
        this.buildingId = buildingId;
        this.registeredByUserId = registeredByUserId;
    }

    public Refill(Instant refillDate, Double liters, Double costSoles, String supplierName, String invoiceNumber, Long buildingId, Long registeredByUserId) {
        this(null, refillDate, liters, costSoles, supplierName, invoiceNumber, buildingId, registeredByUserId);
    }

    public Refill(CreateRefillCommand command) {
        this(command.refillDate(), command.liters(), command.costSoles(), command.supplierName(), command.invoiceNumber(), command.buildingId(), command.registeredByUserId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getRefillDate() {
        return refillDate;
    }

    public Double getLiters() {
        return liters;
    }

    public Double getCostSoles() {
        return costSoles;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public Long getRegisteredByUserId() {
        return registeredByUserId;
    }

    public void onCreated() {
        registerDomainEvent(RefillCreatedEvent.from(this));
    }
}
