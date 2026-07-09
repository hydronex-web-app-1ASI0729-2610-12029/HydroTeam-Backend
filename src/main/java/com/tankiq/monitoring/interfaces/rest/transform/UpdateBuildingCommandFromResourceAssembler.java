package com.tankiq.monitoring.interfaces.rest.transform;

import com.tankiq.monitoring.domain.model.commands.UpdateBuildingCommand;
import com.tankiq.monitoring.interfaces.rest.resources.UpdateBuildingResource;

public class UpdateBuildingCommandFromResourceAssembler {
    public static UpdateBuildingCommand toCommandFromResource(Long buildingId, UpdateBuildingResource resource) {
        return new UpdateBuildingCommand(buildingId, resource.name(), resource.address(), resource.district());
    }
}
