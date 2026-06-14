package com.tankiq.iam.application.commandservices;

import com.tankiq.iam.domain.model.aggregates.User;
import com.tankiq.iam.domain.model.commands.CreateUserCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

public interface UserCommandService {
    Result<User, ApplicationError> handle(CreateUserCommand command);
}
