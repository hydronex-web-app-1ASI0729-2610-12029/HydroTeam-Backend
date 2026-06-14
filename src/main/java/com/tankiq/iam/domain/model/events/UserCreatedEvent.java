package com.tankiq.iam.domain.model.events;

import com.tankiq.iam.domain.model.aggregates.User;

public record UserCreatedEvent(
        Long userId,
        String name,
        String email,
        String passwordHash,
        String phoneNumber
) {
    public static UserCreatedEvent from(User user) {
        return new UserCreatedEvent(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getPhoneNumber()
        );
    }
}
