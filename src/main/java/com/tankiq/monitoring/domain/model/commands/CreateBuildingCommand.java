package com.tankiq.monitoring.domain.model.commands;

public record CreateBuildingCommand(
        String name,
        String address,
        String district
) {
}
