package com.tankiq.iam.application.internal.commandservices;

import com.tankiq.iam.application.commandservices.UserCommandService;
import com.tankiq.iam.domain.model.aggregates.User;
import com.tankiq.iam.domain.model.commands.CreateUserCommand;
import com.tankiq.iam.domain.repositories.UserRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Result<User, ApplicationError> handle(CreateUserCommand command) {
        try {
            var user = new User(command);
            var savedUser = userRepository.save(user);
            return Result.success(savedUser);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("User", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("User creation", e.getMessage()));
        }
    }
}
