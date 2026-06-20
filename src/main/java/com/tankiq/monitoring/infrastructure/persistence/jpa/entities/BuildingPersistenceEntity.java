package com.tankiq.monitoring.infrastructure.persistence.jpa.entities;

import com.tankiq.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "buildings")
public class BuildingPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "district", nullable = false)
    private String district;

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getDistrict() { return district; }

    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setDistrict(String district) { this.district = district; }
}
