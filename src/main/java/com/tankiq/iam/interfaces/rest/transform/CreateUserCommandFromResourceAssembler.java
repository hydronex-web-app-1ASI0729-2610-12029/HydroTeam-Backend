package com.tankiq.iam.interfaces.rest.transform;

import com.tankiq.iam.domain.model.commands.CreateUserCommand;
import com.tankiq.iam.interfaces.rest.resources.CreateUserResource;

public final class CreateUserCommandFromResourceAssembler {
    private CreateUserCommandFromResourceAssembler() {
    }

    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.name(),
                resource.email(),
                resource.passwordHash(),
                resource.phoneNumber()
        );
    }
}
