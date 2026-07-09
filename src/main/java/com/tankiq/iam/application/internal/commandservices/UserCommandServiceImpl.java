package com.tankiq.iam.application.internal.commandservices;

import com.tankiq.iam.application.commandservices.UserCommandService;
import com.tankiq.iam.application.internal.outboundservices.hashing.HashingService;
import com.tankiq.iam.application.internal.outboundservices.tokens.TokenService;
import com.tankiq.iam.domain.model.aggregates.User;
import com.tankiq.iam.domain.model.commands.CreateUserCommand;
import com.tankiq.iam.domain.model.commands.SignInCommand;
import com.tankiq.iam.domain.model.commands.SignUpCommand;
import com.tankiq.iam.domain.repositories.UserRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
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

    @Override
    public Result<ImmutablePair<User, String>, ApplicationError> handle(SignInCommand command) {
        var userOptional = userRepository.findByEmail(command.email());
        if (userOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("User", command.email()));
        }
        var user = userOptional.get();
        if (!hashingService.matches(command.password(), user.getPasswordHash())) {
            return Result.failure(ApplicationError.validationError("credentials", "Invalid email or password"));
        }
        var token = tokenService.generateToken(user.getEmail());
        return Result.success(ImmutablePair.of(user, token));
    }

    @Override
    public Result<User, ApplicationError> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            return Result.failure(ApplicationError.conflict("User", "Email already registered"));
        }
        var hashedPassword = hashingService.encode(command.password());
        var user = new User(command.name(), command.email(), hashedPassword, command.phoneNumber());
        var savedUser = userRepository.save(user);
        return Result.success(savedUser);
    }
}