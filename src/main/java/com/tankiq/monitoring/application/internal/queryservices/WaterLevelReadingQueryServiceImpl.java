package com.tankiq.monitoring.application.internal.queryservices;

import com.tankiq.monitoring.application.queryservices.WaterLevelReadingQueryService;
import com.tankiq.monitoring.domain.model.aggregates.WaterLevelReading;
import com.tankiq.monitoring.domain.model.queries.GetAllWaterLevelReadingsQuery;
import com.tankiq.monitoring.domain.model.queries.GetWaterLevelReadingByIdQuery;
import com.tankiq.monitoring.domain.model.queries.GetWaterLevelReadingsBySensorIdQuery;
import com.tankiq.monitoring.domain.repositories.WaterLevelReadingRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterLevelReadingQueryServiceImpl implements WaterLevelReadingQueryService {
    private final WaterLevelReadingRepository waterLevelReadingRepository;

    public WaterLevelReadingQueryServiceImpl(WaterLevelReadingRepository waterLevelReadingRepository) {
        this.waterLevelReadingRepository = waterLevelReadingRepository;
    }

    @Override
    public Result<List<WaterLevelReading>, ApplicationError> handle(GetAllWaterLevelReadingsQuery query) {
        return Result.success(waterLevelReadingRepository.findAll());
    }

    @Override
    public Result<WaterLevelReading, ApplicationError> handle(GetWaterLevelReadingByIdQuery query) {
        return waterLevelReadingRepository.findById(query.readingId())
                .<Result<WaterLevelReading, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.validationError("WaterLevelReading", "WaterLevelReading with id '%s' was not found".formatted(query.readingId()))));
    }

    @Override
    public Result<List<WaterLevelReading>, ApplicationError> handle(GetWaterLevelReadingsBySensorIdQuery query) {
        return Result.success(waterLevelReadingRepository.findBySensorId(query.sensorId()));
    }
}
