package com.tankiq.monitoring.application.queryservices;

import com.tankiq.monitoring.domain.model.aggregates.WaterLevelReading;
import com.tankiq.monitoring.domain.model.queries.GetAllWaterLevelReadingsQuery;
import com.tankiq.monitoring.domain.model.queries.GetWaterLevelReadingByIdQuery;
import com.tankiq.monitoring.domain.model.queries.GetWaterLevelReadingsBySensorIdQuery;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

import java.util.List;

public interface WaterLevelReadingQueryService {
    Result<List<WaterLevelReading>, ApplicationError> handle(GetAllWaterLevelReadingsQuery query);
    Result<WaterLevelReading, ApplicationError> handle(GetWaterLevelReadingByIdQuery query);
    Result<List<WaterLevelReading>, ApplicationError> handle(GetWaterLevelReadingsBySensorIdQuery query);
}
