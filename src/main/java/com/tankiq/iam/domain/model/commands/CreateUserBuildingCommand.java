package com.tankiq.iam.domain.model.commands;

import java.time.LocalDateTime;

public record CreateUserBuildingCommand(
        Long userId,
        Long buildingId,
        String role,
        String apartmentNumber,
        LocalDateTime associatedAt
) {
}
