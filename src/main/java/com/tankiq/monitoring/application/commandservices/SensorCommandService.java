package com.tankiq.monitoring.application.commandservices;

import com.tankiq.monitoring.domain.model.aggregates.Sensor;
import com.tankiq.monitoring.domain.model.commands.CreateSensorCommand;
import com.tankiq.monitoring.domain.model.commands.DeleteSensorCommand;
import com.tankiq.monitoring.domain.model.commands.UpdateSensorCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

public interface SensorCommandService {
    Result<Sensor, ApplicationError> handle(CreateSensorCommand command);
    Result<Sensor, ApplicationError> handle(UpdateSensorCommand command);
    Result<Void, ApplicationError> handle(DeleteSensorCommand command);
}
