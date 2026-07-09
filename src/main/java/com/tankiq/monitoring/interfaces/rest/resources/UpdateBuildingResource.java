package com.tankiq.monitoring.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record UpdateBuildingResource(
        @NotBlank String name,
        @NotBlank String address,
        @NotBlank String district
) {
}
