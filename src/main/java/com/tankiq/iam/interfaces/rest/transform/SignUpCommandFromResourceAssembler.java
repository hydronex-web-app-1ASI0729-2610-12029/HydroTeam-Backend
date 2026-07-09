package com.tankiq.iam.interfaces.rest.transform;

import com.tankiq.iam.domain.model.commands.SignUpCommand;
import com.tankiq.iam.interfaces.rest.resources.SignUpResource;

public final class SignUpCommandFromResourceAssembler {
    private SignUpCommandFromResourceAssembler() {
    }

    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.name(), resource.email(), resource.password(), resource.phoneNumber());
    }
}