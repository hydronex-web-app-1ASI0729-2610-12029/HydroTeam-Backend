package com.tankiq.monitoring.application.internal.commandservices;

import com.tankiq.monitoring.application.commandservices.SensorCommandService;
import com.tankiq.monitoring.domain.model.aggregates.Sensor;
import com.tankiq.monitoring.domain.model.commands.CreateSensorCommand;
import com.tankiq.monitoring.domain.model.commands.DeleteSensorCommand;
import com.tankiq.monitoring.domain.model.commands.UpdateSensorCommand;
import com.tankiq.monitoring.domain.repositories.SensorRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class SensorCommandServiceImpl implements SensorCommandService {
    private final SensorRepository sensorRepository;

    public SensorCommandServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Result<Sensor, ApplicationError> handle(CreateSensorCommand command) {
        try {
            var sensor = new Sensor(command);
            var savedSensor = sensorRepository.save(sensor);
            return Result.success(savedSensor);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Sensor", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Sensor creation", e.getMessage()));
        }
    }

    @Override
    public Result<Sensor, ApplicationError> handle(UpdateSensorCommand command) {
        var optional = sensorRepository.findById(command.sensorId());
        if (optional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Sensor", command.sensorId()));
        }
        var sensor = optional.get();
        sensor.update(command.hardwareId(), command.type(), command.status(), command.lastSyncAt(), command.cisternId());
        var updated = sensorRepository.save(sensor);
        return Result.success(updated);
    }

    @Override
    public Result<Void, ApplicationError> handle(DeleteSensorCommand command) {
        if (sensorRepository.findById(command.sensorId()).isEmpty()) {
            return Result.failure(ApplicationError.notFound("Sensor", command.sensorId()));
        }
        sensorRepository.deleteById(command.sensorId());
        return Result.success(null);
    }
}
