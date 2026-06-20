package com.tankiq.monitoring.application.internal.queryservices;

import com.tankiq.monitoring.application.queryservices.BuildingQueryService;
import com.tankiq.monitoring.domain.model.aggregates.Building;
import com.tankiq.monitoring.domain.model.queries.GetAllBuildingsQuery;
import com.tankiq.monitoring.domain.model.queries.GetBuildingByIdQuery;
import com.tankiq.monitoring.domain.repositories.BuildingRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingQueryServiceImpl implements BuildingQueryService {
    private final BuildingRepository buildingRepository;

    public BuildingQueryServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public Result<List<Building>, ApplicationError> handle(GetAllBuildingsQuery query) {
        return Result.success(buildingRepository.findAll());
    }

    @Override
    public Result<Building, ApplicationError> handle(GetBuildingByIdQuery query) {
        return buildingRepository.findById(query.buildingId())
                .<Result<Building, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.validationError("Building", "Building with id '%s' was not found".formatted(query.buildingId()))));
    }
}
