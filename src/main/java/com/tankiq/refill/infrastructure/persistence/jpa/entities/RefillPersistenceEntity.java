package com.tankiq.refill.infrastructure.persistence.jpa.entities;

import com.tankiq.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "refills")
public class RefillPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "refill_date")
    private Instant refillDate;

    @Column(name = "liters")
    private Double liters;

    @Column(name = "cost_soles")
    private Double costSoles;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "building_id")
    private Long buildingId;

    @Column(name = "registered_by_user_id")
    private Long registeredByUserId;

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

    public void setRefillDate(Instant refillDate) {
        this.refillDate = refillDate;
    }

    public void setLiters(Double liters) {
        this.liters = liters;
    }

    public void setCostSoles(Double costSoles) {
        this.costSoles = costSoles;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public void setRegisteredByUserId(Long registeredByUserId) {
        this.registeredByUserId = registeredByUserId;
    }

}
