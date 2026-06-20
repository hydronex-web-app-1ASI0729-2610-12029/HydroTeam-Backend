package com.tankiq.iam.interfaces.rest.transform;

import com.tankiq.iam.domain.model.commands.CreateUserBuildingCommand;
import com.tankiq.iam.interfaces.rest.resources.CreateUserBuildingResource;

public final class CreateUserBuildingCommandFromResourceAssembler {

    private CreateUserBuildingCommandFromResourceAssembler() {
    }

    public static CreateUserBuildingCommand toCommandFromResource(CreateUserBuildingResource resource) {
        return new CreateUserBuildingCommand(
                resource.userId(),
                resource.buildingId(),
                resource.role(),
                resource.apartmentNumber()
        );
    }
}
