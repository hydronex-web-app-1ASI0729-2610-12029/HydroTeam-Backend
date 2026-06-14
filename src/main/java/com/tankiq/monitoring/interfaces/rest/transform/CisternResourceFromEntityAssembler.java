package com.tankiq.monitoring.interfaces.rest.transform;

import com.tankiq.monitoring.domain.model.aggregates.Cistern;
import com.tankiq.monitoring.interfaces.rest.resources.CisternResource;

public final class CisternResourceFromEntityAssembler {
    private CisternResourceFromEntityAssembler() {
    }

    public static CisternResource toResourceFromEntity(Cistern cistern) {
        return new CisternResource(
                cistern.getId(),
                cistern.getCapacityLiters(),
                cistern.getCurrentLevelPercent(),
                cistern.getAlertThresholdPercent(),
                cistern.getBuildingId()
        );
    }
}
