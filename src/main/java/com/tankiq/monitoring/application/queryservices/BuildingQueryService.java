package com.tankiq.monitoring.application.queryservices;

import com.tankiq.monitoring.domain.model.aggregates.Building;
import com.tankiq.monitoring.domain.model.queries.GetAllBuildingsQuery;
import com.tankiq.monitoring.domain.model.queries.GetBuildingByIdQuery;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

import java.util.List;

public interface BuildingQueryService {
    Result<List<Building>, ApplicationError> handle(GetAllBuildingsQuery query);
    Result<Building, ApplicationError> handle(GetBuildingByIdQuery query);
}
