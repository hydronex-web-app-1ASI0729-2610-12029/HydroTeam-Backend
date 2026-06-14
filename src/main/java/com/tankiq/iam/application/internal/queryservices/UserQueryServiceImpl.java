package com.tankiq.iam.application.internal.queryservices;

import com.tankiq.iam.application.queryservices.UserQueryService;
import com.tankiq.iam.domain.model.aggregates.User;
import com.tankiq.iam.domain.model.queries.GetAllUsersQuery;
import com.tankiq.iam.domain.model.queries.GetUserByIdQuery;
import com.tankiq.iam.domain.repositories.UserRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Result<List<User>, ApplicationError> handle(GetAllUsersQuery query) {
        return Result.success(userRepository.findAll());
    }

    @Override
    public Result<User, ApplicationError> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId())
                .<Result<User, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.validationError("User", "User with id '%s' was not found".formatted(query.userId()))));
    }
}
