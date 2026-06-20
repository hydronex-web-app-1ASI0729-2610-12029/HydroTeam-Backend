package com.tankiq.iam.application.internal.queryservices;

import com.tankiq.iam.application.queryservices.UserBuildingQueryService;
import com.tankiq.iam.domain.model.aggregates.UserBuilding;
import com.tankiq.iam.domain.model.queries.GetAllUserBuildingsQuery;
import com.tankiq.iam.domain.model.queries.GetUserBuildingByIdQuery;
import com.tankiq.iam.domain.repositories.UserBuildingRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBuildingQueryServiceImpl implements UserBuildingQueryService {

    private final UserBuildingRepository userBuildingRepository;

    public UserBuildingQueryServiceImpl(UserBuildingRepository userBuildingRepository) {
        this.userBuildingRepository = userBuildingRepository;
    }

    @Override
    public Result<List<UserBuilding>, ApplicationError> handle(GetAllUserBuildingsQuery query) {
        return Result.success(userBuildingRepository.findAll());
    }

    @Override
    public Result<UserBuilding, ApplicationError> handle(GetUserBuildingByIdQuery query) {
        return userBuildingRepository.findById(query.userBuildingId())
                .<Result<UserBuilding, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.validationError(
                        "UserBuilding",
                        "UserBuilding with id '%s' was not found".formatted(query.userBuildingId())
                )));
    }
}
