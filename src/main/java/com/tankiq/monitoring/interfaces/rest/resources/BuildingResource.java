package com.tankiq.monitoring.interfaces.rest.resources;

public record BuildingResource(
        Long id,
        String name,
        String address,
        String district
) {
}
