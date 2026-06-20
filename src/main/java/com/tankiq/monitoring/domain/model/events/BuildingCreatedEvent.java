package com.tankiq.monitoring.domain.model.events;

import com.tankiq.monitoring.domain.model.aggregates.Building;

public record BuildingCreatedEvent(Long buildingId, String name) {
    public static BuildingCreatedEvent from(Building building) {
        return new BuildingCreatedEvent(building.getId(), building.getName());
    }
}
