package com.tankiq.refill.application.commandservices;

import com.tankiq.refill.domain.model.aggregates.Refill;
import com.tankiq.refill.domain.model.commands.CreateRefillCommand;
import com.tankiq.refill.domain.model.commands.DeleteRefillCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

public interface RefillCommandService {
    Result<Refill, ApplicationError> handle(CreateRefillCommand command);
    Result<Void, ApplicationError> handle(DeleteRefillCommand command);
}
