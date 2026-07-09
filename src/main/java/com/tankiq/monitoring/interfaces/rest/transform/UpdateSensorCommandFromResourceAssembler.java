package com.tankiq.monitoring.interfaces.rest.transform;

import com.tankiq.monitoring.domain.model.commands.UpdateSensorCommand;
import com.tankiq.monitoring.interfaces.rest.resources.UpdateSensorResource;

public class UpdateSensorCommandFromResourceAssembler {
    public static UpdateSensorCommand toCommandFromResource(Long sensorId, UpdateSensorResource resource) {
        return new UpdateSensorCommand(sensorId, resource.hardwareId(), resource.type(), resource.status(), resource.lastSyncAt(), resource.cisternId());
    }
}
