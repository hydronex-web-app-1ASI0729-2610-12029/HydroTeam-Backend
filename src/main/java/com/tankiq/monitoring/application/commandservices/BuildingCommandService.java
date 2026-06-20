package com.tankiq.monitoring.application.commandservices;

import com.tankiq.monitoring.domain.model.aggregates.Building;
import com.tankiq.monitoring.domain.model.commands.CreateBuildingCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

public interface BuildingCommandService {
    Result<Building, ApplicationError> handle(CreateBuildingCommand command);
}
