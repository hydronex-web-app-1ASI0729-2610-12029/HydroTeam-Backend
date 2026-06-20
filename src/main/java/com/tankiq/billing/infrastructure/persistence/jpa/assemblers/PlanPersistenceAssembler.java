package com.tankiq.billing.infrastructure.persistence.jpa.assemblers;

import com.tankiq.billing.domain.model.aggregates.Plan;
import com.tankiq.billing.infrastructure.persistence.jpa.entities.PlanPersistenceEntity;

/**
 * Plan Assembler
 * <description>
 *     This is the assembler responsible for transforming the database entity into a resource, and vice versa.
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
public final class PlanPersistenceAssembler {
    public static Plan toDomainFromPersistence(PlanPersistenceEntity entity) {
        if (entity == null) return null;

        return new Plan(
                entity.getId(),
                entity.getName(),
                entity.getPriceSoles(),
                entity.getFeatures(),
                entity.getMaxSensors()
        );
    }

    public static PlanPersistenceEntity toPersistenceFromDomain(Plan plan){
        if (plan == null) return null;

        PlanPersistenceEntity entity = new PlanPersistenceEntity();

        entity.setId(plan.getId());
        entity.setName(plan.getName());
        entity.setPriceSoles(plan.getPriceSoles());
        entity.setFeatures(plan.getFeatures());
        entity.setMaxSensors(plan.getMaxSensors());

        return  entity;
    }
}
