package com.tankiq.iam.application.internal.commandservices;

import com.tankiq.iam.application.commandservices.UserBuildingCommandService;
import com.tankiq.iam.domain.model.aggregates.UserBuilding;
import com.tankiq.iam.domain.model.commands.CreateUserBuildingCommand;
import com.tankiq.iam.domain.repositories.UserBuildingRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class UserBuildingCommandServiceImpl implements UserBuildingCommandService {

    private final UserBuildingRepository userBuildingRepository;

    public UserBuildingCommandServiceImpl(UserBuildingRepository userBuildingRepository) {
        this.userBuildingRepository = userBuildingRepository;
    }

    @Override
    public Result<UserBuilding, ApplicationError> handle(CreateUserBuildingCommand command) {
        try {
            var userBuilding = new UserBuilding(command);
            var savedUserBuilding = userBuildingRepository.save(userBuilding);
            return Result.success(savedUserBuilding);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("UserBuilding", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("UserBuilding creation", e.getMessage()));
        }
    }
}
