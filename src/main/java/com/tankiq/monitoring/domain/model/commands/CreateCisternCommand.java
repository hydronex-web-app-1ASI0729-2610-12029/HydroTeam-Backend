package com.tankiq.monitoring.domain.model.commands;

public record CreateCisternCommand(
        Double capacityLiters,
        Double currentLevelPercent,
        Double alertThresholdPercent,
        Long buildingId
) {
}
