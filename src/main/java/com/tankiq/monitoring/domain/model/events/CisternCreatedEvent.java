package com.tankiq.monitoring.domain.model.events;

import com.tankiq.monitoring.domain.model.aggregates.Cistern;

public record CisternCreatedEvent(
        Long cisternId,
        Double capacityLiters,
        Double currentLevelPercent,
        Double alertThresholdPercent,
        Long buildingId
) {
    public static CisternCreatedEvent from(Cistern cistern) {
        return new CisternCreatedEvent(
                cistern.getId(),
                cistern.getCapacityLiters(),
                cistern.getCurrentLevelPercent(),
                cistern.getAlertThresholdPercent(),
                cistern.getBuildingId()
        );
    }
}
