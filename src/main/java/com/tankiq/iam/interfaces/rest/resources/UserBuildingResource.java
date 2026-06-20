package com.tankiq.iam.interfaces.rest.resources;

import java.time.LocalDateTime;

public record UserBuildingResource(
        Long id,
        Long userId,
        Long buildingId,
        String role,
        String apartmentNumber,
        LocalDateTime associatedAt
) {
}
