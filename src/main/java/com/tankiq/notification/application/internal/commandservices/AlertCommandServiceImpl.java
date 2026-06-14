package com.tankiq.notification.application.internal.commandservices;

import com.tankiq.notification.application.commandservices.AlertCommandService;
import com.tankiq.notification.domain.model.aggregates.Alert;
import com.tankiq.notification.domain.model.commands.CreateAlertCommand;
import com.tankiq.notification.domain.repositories.AlertRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class AlertCommandServiceImpl implements AlertCommandService {
    private final AlertRepository alertRepository;

    public AlertCommandServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public Result<Alert, ApplicationError> handle(CreateAlertCommand command) {
        try {
            var alert = new Alert(command);
            var savedAlert = alertRepository.save(alert);
            return Result.success(savedAlert);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Alert", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Alert creation", e.getMessage()));
        }
    }
}
