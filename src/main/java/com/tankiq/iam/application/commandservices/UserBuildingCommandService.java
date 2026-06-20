package com.tankiq.iam.application.commandservices;

import com.tankiq.iam.domain.model.aggregates.UserBuilding;
import com.tankiq.iam.domain.model.commands.CreateUserBuildingCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

public interface UserBuildingCommandService {
    Result<UserBuilding, ApplicationError> handle(CreateUserBuildingCommand command);
}
