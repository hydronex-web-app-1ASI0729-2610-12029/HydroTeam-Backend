package com.tankiq.monitoring.application.internal.commandservices;

import com.tankiq.monitoring.application.commandservices.WaterLevelReadingCommandService;
import com.tankiq.monitoring.domain.model.aggregates.WaterLevelReading;
import com.tankiq.monitoring.domain.model.commands.CreateWaterLevelReadingCommand;
import com.tankiq.monitoring.domain.repositories.WaterLevelReadingRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class WaterLevelReadingCommandServiceImpl implements WaterLevelReadingCommandService {
    private final WaterLevelReadingRepository waterLevelReadingRepository;

    public WaterLevelReadingCommandServiceImpl(WaterLevelReadingRepository waterLevelReadingRepository) {
        this.waterLevelReadingRepository = waterLevelReadingRepository;
    }

    @Override
    public Result<WaterLevelReading, ApplicationError> handle(CreateWaterLevelReadingCommand command) {
        try {
            var reading = new WaterLevelReading(command);
            var savedReading = waterLevelReadingRepository.save(reading);
            return Result.success(savedReading);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("WaterLevelReading", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("WaterLevelReading creation", e.getMessage()));
        }
    }
}
