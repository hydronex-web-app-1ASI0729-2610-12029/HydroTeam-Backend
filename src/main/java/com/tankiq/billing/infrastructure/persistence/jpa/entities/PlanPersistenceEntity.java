package com.tankiq.billing.infrastructure.persistence.jpa.entities;

import com.tankiq.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Plan Entity
 * <description>
 *     This in the entity Plan to persistence in the database
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
@Setter
@Getter
@Entity
@Table(name = "plans")
public class PlanPersistenceEntity extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double priceSoles;

    @Column(columnDefinition = "TEXT")
    private String features;

    @Column(nullable = false)
    private Integer maxSensors;

    public PlanPersistenceEntity() {}

    public PlanPersistenceEntity(String name, Double priceSoles, String features, Integer maxSensors) {
        this.name = name;
        this.priceSoles = priceSoles;
        this.features = features;
        this.maxSensors = maxSensors;
    }
}
