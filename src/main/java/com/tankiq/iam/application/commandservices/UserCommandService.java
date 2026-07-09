package com.tankiq.iam.application.commandservices;

import com.tankiq.iam.domain.model.aggregates.User;
import com.tankiq.iam.domain.model.commands.CreateUserCommand;
import com.tankiq.iam.domain.model.commands.SignInCommand;
import com.tankiq.iam.domain.model.commands.SignUpCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.apache.commons.lang3.tuple.ImmutablePair;

public interface UserCommandService {
    Result<User, ApplicationError> handle(CreateUserCommand command);
    Result<ImmutablePair<User, String>, ApplicationError> handle(SignInCommand command);
    Result<User, ApplicationError> handle(SignUpCommand command);
}