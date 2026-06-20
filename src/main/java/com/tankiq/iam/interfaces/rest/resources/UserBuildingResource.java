package com.tankiq.iam.interfaces.rest.resources;

public record UserBuildingResource(
        Long id,
        Long userId,
        Long buildingId,
        String role,
        String apartmentNumber
) {
}
