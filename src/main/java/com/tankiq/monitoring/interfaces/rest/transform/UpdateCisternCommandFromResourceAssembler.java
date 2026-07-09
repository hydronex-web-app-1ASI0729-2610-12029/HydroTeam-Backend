package com.tankiq.monitoring.interfaces.rest.transform;

import com.tankiq.monitoring.domain.model.commands.UpdateCisternCommand;
import com.tankiq.monitoring.interfaces.rest.resources.UpdateCisternResource;

public class UpdateCisternCommandFromResourceAssembler {
    public static UpdateCisternCommand toCommandFromResource(Long cisternId, UpdateCisternResource resource) {
        return new UpdateCisternCommand(cisternId, resource.capacityLiters(), resource.currentLevelPercent(), resource.alertThresholdPercent(), resource.buildingId());
    }
}
