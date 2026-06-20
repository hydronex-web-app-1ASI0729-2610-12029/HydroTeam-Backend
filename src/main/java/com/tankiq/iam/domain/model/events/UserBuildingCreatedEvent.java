package com.tankiq.iam.domain.model.events;

import com.tankiq.iam.domain.model.aggregates.UserBuilding;

public record UserBuildingCreatedEvent(
        Long userBuildingId,
        Long userId,
        Long buildingId,
        String role,
        String apartmentNumber
) {
    public static UserBuildingCreatedEvent from(UserBuilding userBuilding) {
        return new UserBuildingCreatedEvent(
                userBuilding.getId(),
                userBuilding.getUserId(),
                userBuilding.getBuildingId(),
                userBuilding.getRole(),
                userBuilding.getApartmentNumber()
        );
    }
}
