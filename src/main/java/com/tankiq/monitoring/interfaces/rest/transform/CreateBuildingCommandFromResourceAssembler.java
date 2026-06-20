package com.tankiq.monitoring.interfaces.rest.transform;

import com.tankiq.monitoring.domain.model.commands.CreateBuildingCommand;
import com.tankiq.monitoring.interfaces.rest.resources.CreateBuildingResource;

public final class CreateBuildingCommandFromResourceAssembler {
    private CreateBuildingCommandFromResourceAssembler() {
    }

    public static CreateBuildingCommand toCommandFromResource(CreateBuildingResource resource) {
        return new CreateBuildingCommand(resource.name(), resource.address(), resource.district());
    }
}
