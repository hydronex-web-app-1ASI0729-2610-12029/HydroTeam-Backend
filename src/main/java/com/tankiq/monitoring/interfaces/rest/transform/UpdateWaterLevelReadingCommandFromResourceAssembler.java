package com.tankiq.monitoring.interfaces.rest.transform;

import com.tankiq.monitoring.domain.model.commands.UpdateWaterLevelReadingCommand;
import com.tankiq.monitoring.interfaces.rest.resources.UpdateWaterLevelReadingResource;

public class UpdateWaterLevelReadingCommandFromResourceAssembler {
    public static UpdateWaterLevelReadingCommand toCommandFromResource(Long readingId, UpdateWaterLevelReadingResource resource) {
        return new UpdateWaterLevelReadingCommand(readingId, resource.levelPercent(), resource.volumeLiters(), resource.recordedAt(), resource.sensorId());
    }
}
