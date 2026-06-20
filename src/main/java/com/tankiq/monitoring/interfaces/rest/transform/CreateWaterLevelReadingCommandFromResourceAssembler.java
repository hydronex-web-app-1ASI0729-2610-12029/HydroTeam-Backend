package com.tankiq.monitoring.interfaces.rest.transform;

import com.tankiq.monitoring.domain.model.commands.CreateWaterLevelReadingCommand;
import com.tankiq.monitoring.interfaces.rest.resources.CreateWaterLevelReadingResource;

public final class CreateWaterLevelReadingCommandFromResourceAssembler {
    private CreateWaterLevelReadingCommandFromResourceAssembler() {
    }

    public static CreateWaterLevelReadingCommand toCommandFromResource(CreateWaterLevelReadingResource resource) {
        return new CreateWaterLevelReadingCommand(resource.levelPercent(), resource.volumeLiters(), resource.recordedAt(), resource.sensorId());
    }
}
