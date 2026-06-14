package com.tankiq.monitoring.interfaces.rest.resources;

public record CisternResource(
        Long id,
        Double capacityLiters,
        Double currentLevelPercent,
        Double alertThresholdPercent,
        Long buildingId
) {
}
