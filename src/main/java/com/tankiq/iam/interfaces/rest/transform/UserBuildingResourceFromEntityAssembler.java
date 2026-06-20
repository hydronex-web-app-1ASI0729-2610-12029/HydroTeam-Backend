package com.tankiq.iam.interfaces.rest.transform;

import com.tankiq.iam.domain.model.aggregates.UserBuilding;
import com.tankiq.iam.interfaces.rest.resources.UserBuildingResource;

public final class UserBuildingResourceFromEntityAssembler {

    private UserBuildingResourceFromEntityAssembler() {
    }

    public static UserBuildingResource toResourceFromEntity(UserBuilding userBuilding) {
        return new UserBuildingResource(
                userBuilding.getId(),
                userBuilding.getUserId(),
                userBuilding.getBuildingId(),
                userBuilding.getRole(),
                userBuilding.getApartmentNumber()
        );
    }
}
