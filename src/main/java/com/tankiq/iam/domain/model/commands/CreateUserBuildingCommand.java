package com.tankiq.iam.domain.model.commands;

public record CreateUserBuildingCommand(
        Long userId,
        Long buildingId,
        String role,
        String apartmentNumber
) {
}
