package com.tankiq.monitoring.application.commandservices;

import com.tankiq.monitoring.domain.model.aggregates.WaterLevelReading;
import com.tankiq.monitoring.domain.model.commands.CreateWaterLevelReadingCommand;
import com.tankiq.monitoring.domain.model.commands.DeleteWaterLevelReadingCommand;
import com.tankiq.monitoring.domain.model.commands.UpdateWaterLevelReadingCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

public interface WaterLevelReadingCommandService {
    Result<WaterLevelReading, ApplicationError> handle(CreateWaterLevelReadingCommand command);
    Result<WaterLevelReading, ApplicationError> handle(UpdateWaterLevelReadingCommand command);
    Result<Void, ApplicationError> handle(DeleteWaterLevelReadingCommand command);
}
