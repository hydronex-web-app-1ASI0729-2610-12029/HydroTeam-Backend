package com.tankiq.monitoring.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record CreateBuildingResource(
        @NotBlank String name,
        @NotBlank String address,
        @NotBlank String district
) {
}
