package com.tankiq.notification.application.commandservices;

import com.tankiq.notification.domain.model.aggregates.Alert;
import com.tankiq.notification.domain.model.commands.CreateAlertCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

public interface AlertCommandService {
    Result<Alert, ApplicationError> handle(CreateAlertCommand command);
}
