package com.tankiq.billing.interfaces.rest.transform;

import com.tankiq.billing.domain.model.aggregates.Plan;
import com.tankiq.billing.interfaces.rest.resources.PlanResource;

public class PlanResourceFromEntityAssembler {
    public static PlanResource toResourceFromEntity(Plan entity) {
        return new PlanResource(
                entity.getId(),
                entity.getName(),
                entity.getPriceSoles(),
                entity.getFeatures(),
                entity.getMaxSensors()
        );
    }
}
