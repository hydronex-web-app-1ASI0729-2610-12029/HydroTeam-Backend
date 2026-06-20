package com.tankiq.iam.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateUserBuildingResource(
        @NotNull Long userId,
        @NotNull Long buildingId,
        @NotBlank String role,
        String apartmentNumber,
        LocalDateTime associatedAt
) {
}
