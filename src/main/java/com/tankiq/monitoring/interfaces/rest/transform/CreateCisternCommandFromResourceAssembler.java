package com.tankiq.monitoring.interfaces.rest.transform;

import com.tankiq.monitoring.domain.model.commands.CreateCisternCommand;
import com.tankiq.monitoring.interfaces.rest.resources.CreateCisternResource;

public final class CreateCisternCommandFromResourceAssembler {
    private CreateCisternCommandFromResourceAssembler() {
    }

    public static CreateCisternCommand toCommandFromResource(CreateCisternResource resource) {
        return new CreateCisternCommand(
                resource.capacityLiters(),
                resource.currentLevelPercent(),
                resource.alertThresholdPercent(),
                resource.buildingId()
        );
    }
}
