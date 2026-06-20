package com.tankiq.notification.application.internal.queryservices;

import com.tankiq.notification.application.queryservices.AlertQueryService;
import com.tankiq.notification.domain.model.aggregates.Alert;
import com.tankiq.notification.domain.model.queries.GetActiveAlertsByCisternIdQuery;
import com.tankiq.notification.domain.model.queries.GetAllAlertsQuery;
import com.tankiq.notification.domain.model.queries.GetAlertByIdQuery;
import com.tankiq.notification.domain.repositories.AlertRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertQueryServiceImpl implements AlertQueryService {
    private final AlertRepository alertRepository;

    public AlertQueryServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public Result<List<Alert>, ApplicationError> handle(GetAllAlertsQuery query) {
        return Result.success(alertRepository.findAll());
    }

    @Override
    public Result<Alert, ApplicationError> handle(GetAlertByIdQuery query) {
        return alertRepository.findById(query.alertId())
                .<Result<Alert, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.validationError("Alert", "Alert with id '%s' was not found".formatted(query.alertId()))));
    }

    @Override
    public Result<List<Alert>, ApplicationError> handle(GetActiveAlertsByCisternIdQuery query) {
        return Result.success(alertRepository.findActiveByCisternId(query.cisternId()));
    }
}