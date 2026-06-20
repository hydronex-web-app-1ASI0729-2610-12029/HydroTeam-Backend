package com.tankiq.monitoring.interfaces.rest.transform;

import com.tankiq.monitoring.domain.model.aggregates.Building;
import com.tankiq.monitoring.interfaces.rest.resources.BuildingResource;

public final class BuildingResourceFromEntityAssembler {
    private BuildingResourceFromEntityAssembler() {
    }

    public static BuildingResource toResourceFromEntity(Building building) {
        return new BuildingResource(building.getId(), building.getName(), building.getAddress(), building.getDistrict());
    }
}
