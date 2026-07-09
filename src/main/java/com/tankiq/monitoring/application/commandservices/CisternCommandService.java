package com.tankiq.monitoring.application.commandservices;

import com.tankiq.monitoring.domain.model.aggregates.Cistern;
import com.tankiq.monitoring.domain.model.commands.CreateCisternCommand;
import com.tankiq.monitoring.domain.model.commands.DeleteCisternCommand;
import com.tankiq.monitoring.domain.model.commands.UpdateCisternCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

public interface CisternCommandService {
    Result<Cistern, ApplicationError> handle(CreateCisternCommand command);
    Result<Cistern, ApplicationError> handle(UpdateCisternCommand command);
    Result<Void, ApplicationError> handle(DeleteCisternCommand command);
}
