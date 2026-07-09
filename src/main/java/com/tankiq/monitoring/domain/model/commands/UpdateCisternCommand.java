package com.tankiq.monitoring.domain.model.commands;

public record UpdateCisternCommand(
        Long cisternId,
        Double capacityLiters,
        Double currentLevelPercent,
        Double alertThresholdPercent,
        Long buildingId
) {
}
