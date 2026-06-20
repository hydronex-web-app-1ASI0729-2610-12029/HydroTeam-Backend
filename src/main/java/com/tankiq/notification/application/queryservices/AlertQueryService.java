package com.tankiq.notification.application.queryservices;

import com.tankiq.notification.domain.model.aggregates.Alert;
import com.tankiq.notification.domain.model.queries.GetActiveAlertsByCisternIdQuery;
import com.tankiq.notification.domain.model.queries.GetAllAlertsQuery;
import com.tankiq.notification.domain.model.queries.GetAlertByIdQuery;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

import java.util.List;

public interface AlertQueryService {
    Result<List<Alert>, ApplicationError> handle(GetAllAlertsQuery query);
    Result<Alert, ApplicationError> handle(GetAlertByIdQuery query);
    Result<List<Alert>, ApplicationError> handle(GetActiveAlertsByCisternIdQuery query);
}