package com.tankiq.monitoring.application.internal.queryservices;

import com.tankiq.monitoring.application.queryservices.SensorQueryService;
import com.tankiq.monitoring.domain.model.aggregates.Sensor;
import com.tankiq.monitoring.domain.model.queries.GetAllSensorsQuery;
import com.tankiq.monitoring.domain.model.queries.GetSensorByIdQuery;
import com.tankiq.monitoring.domain.model.queries.GetSensorsByCisternIdQuery;
import com.tankiq.monitoring.domain.repositories.SensorRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorQueryServiceImpl implements SensorQueryService {
    private final SensorRepository sensorRepository;

    public SensorQueryServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Result<List<Sensor>, ApplicationError> handle(GetAllSensorsQuery query) {
        return Result.success(sensorRepository.findAll());
    }

    @Override
    public Result<Sensor, ApplicationError> handle(GetSensorByIdQuery query) {
        return sensorRepository.findById(query.sensorId())
                .<Result<Sensor, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.validationError("Sensor", "Sensor with id '%s' was not found".formatted(query.sensorId()))));
    }

    @Override
    public Result<List<Sensor>, ApplicationError> handle(GetSensorsByCisternIdQuery query) {
        return Result.success(sensorRepository.findByCisternId(query.cisternId()));
    }
}
