package com.tankiq.monitoring.application.internal.commandservices;

import com.tankiq.monitoring.application.commandservices.BuildingCommandService;
import com.tankiq.monitoring.domain.model.aggregates.Building;
import com.tankiq.monitoring.domain.model.commands.CreateBuildingCommand;
import com.tankiq.monitoring.domain.model.commands.DeleteBuildingCommand;
import com.tankiq.monitoring.domain.model.commands.UpdateBuildingCommand;
import com.tankiq.monitoring.domain.repositories.BuildingRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class BuildingCommandServiceImpl implements BuildingCommandService {
    private final BuildingRepository buildingRepository;

    public BuildingCommandServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public Result<Building, ApplicationError> handle(CreateBuildingCommand command) {
        try {
            var building = new Building(command);
            var savedBuilding = buildingRepository.save(building);
            return Result.success(savedBuilding);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Building", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Building creation", e.getMessage()));
        }
    }

    @Override
    public Result<Building, ApplicationError> handle(UpdateBuildingCommand command) {
        var optional = buildingRepository.findById(command.buildingId());
        if (optional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Building", command.buildingId()));
        }
        var building = optional.get();
        building.update(command.name(), command.address(), command.district());
        var updated = buildingRepository.save(building);
        return Result.success(updated);
    }

    @Override
    public Result<Void, ApplicationError> handle(DeleteBuildingCommand command) {
        if (buildingRepository.findById(command.buildingId()).isEmpty()) {
            return Result.failure(ApplicationError.notFound("Building", command.buildingId()));
        }
        buildingRepository.deleteById(command.buildingId());
        return Result.success(null);
    }
}
