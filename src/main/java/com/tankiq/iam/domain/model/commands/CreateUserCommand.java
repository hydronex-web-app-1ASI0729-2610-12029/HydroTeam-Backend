package com.tankiq.iam.domain.model.commands;

public record CreateUserCommand(
        String name,
        String email,
        String passwordHash,
        String phoneNumber
) {
}
