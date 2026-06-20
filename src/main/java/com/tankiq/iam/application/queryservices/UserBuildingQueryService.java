package com.tankiq.iam.application.queryservices;

import com.tankiq.iam.domain.model.aggregates.UserBuilding;
import com.tankiq.iam.domain.model.queries.GetAllUserBuildingsQuery;
import com.tankiq.iam.domain.model.queries.GetUserBuildingByIdQuery;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

import java.util.List;

public interface UserBuildingQueryService {
    Result<List<UserBuilding>, ApplicationError> handle(GetAllUserBuildingsQuery query);
    Result<UserBuilding, ApplicationError> handle(GetUserBuildingByIdQuery query);
}
