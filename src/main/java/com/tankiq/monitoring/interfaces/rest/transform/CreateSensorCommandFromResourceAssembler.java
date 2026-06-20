package com.tankiq.monitoring.interfaces.rest.transform;

import com.tankiq.monitoring.domain.model.commands.CreateSensorCommand;
import com.tankiq.monitoring.interfaces.rest.resources.CreateSensorResource;

public final class CreateSensorCommandFromResourceAssembler {
    private CreateSensorCommandFromResourceAssembler() {
    }

    public static CreateSensorCommand toCommandFromResource(CreateSensorResource resource) {
        return new CreateSensorCommand(resource.hardwareId(), resource.type(), resource.status(), resource.lastSyncAt(), resource.cisternId());
    }
}
