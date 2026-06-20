package com.tankiq.billing.infrastructure.persistence.jpa.entities;

import com.tankiq.billing.domain.model.valueobjects.SubscriptionStatus;
import com.tankiq.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Subscription entity
 * <description>
 *     This is the subscription entity to persistence in the database.
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
@Getter
@Setter
@Entity
@Table(name = "subscriptions")
public class SubscriptionPersistenceEntity extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus status;

    @Column(nullable = false)
    private Long buildingId;

    @Column(nullable = false)
    private Long planId;

    public SubscriptionPersistenceEntity() {}

    public SubscriptionPersistenceEntity(LocalDate startDate, LocalDate endDate, SubscriptionStatus status, Long buildingId, Long planId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.buildingId = buildingId;
        this.planId = planId;
    }
}