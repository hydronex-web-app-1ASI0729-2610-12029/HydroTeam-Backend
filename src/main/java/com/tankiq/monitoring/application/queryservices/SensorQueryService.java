package com.tankiq.monitoring.application.queryservices;

import com.tankiq.monitoring.domain.model.aggregates.Sensor;
import com.tankiq.monitoring.domain.model.queries.GetAllSensorsQuery;
import com.tankiq.monitoring.domain.model.queries.GetSensorByIdQuery;
import com.tankiq.monitoring.domain.model.queries.GetSensorsByCisternIdQuery;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

import java.util.List;

public interface SensorQueryService {
    Result<List<Sensor>, ApplicationError> handle(GetAllSensorsQuery query);
    Result<Sensor, ApplicationError> handle(GetSensorByIdQuery query);
    Result<List<Sensor>, ApplicationError> handle(GetSensorsByCisternIdQuery query);
}
