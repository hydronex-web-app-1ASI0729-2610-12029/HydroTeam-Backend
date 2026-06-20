package com.tankiq.iam.infrastructure.persistence.jpa.entities;

import com.tankiq.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_buildings")
public class UserBuildingPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "building_id")
    private Long buildingId;

    @Column(name = "role")
    private String role;

    @Column(name = "apartment_number")
    private String apartmentNumber;

    @Column(name = "associated_at")
    private LocalDateTime associatedAt;

    public Long getUserId() { return userId; }
    public Long getBuildingId() { return buildingId; }
    public String getRole() { return role; }
    public String getApartmentNumber() { return apartmentNumber; }
    public LocalDateTime getAssociatedAt() { return associatedAt; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
    public void setRole(String role) { this.role = role; }
    public void setApartmentNumber(String apartmentNumber) { this.apartmentNumber = apartmentNumber; }
    public void setAssociatedAt(LocalDateTime associatedAt) { this.associatedAt = associatedAt; }
}
