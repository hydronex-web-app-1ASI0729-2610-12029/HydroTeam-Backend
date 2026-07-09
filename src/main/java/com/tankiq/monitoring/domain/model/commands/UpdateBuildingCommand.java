package com.tankiq.monitoring.domain.model.commands;

public record UpdateBuildingCommand(
        Long buildingId,
        String name,
        String address,
        String district
) {
}
