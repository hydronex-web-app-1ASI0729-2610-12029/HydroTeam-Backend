package com.tankiq.monitoring.application.commandservices;

import com.tankiq.monitoring.domain.model.aggregates.Sensor;
import com.tankiq.monitoring.domain.model.commands.CreateSensorCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

public interface SensorCommandService {
    Result<Sensor, ApplicationError> handle(CreateSensorCommand command);
}
