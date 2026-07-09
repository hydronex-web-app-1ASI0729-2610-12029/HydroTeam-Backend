package com.tankiq.iam.interfaces.rest.transform;

import com.tankiq.iam.domain.model.commands.SignInCommand;
import com.tankiq.iam.interfaces.rest.resources.SignInResource;

public final class SignInCommandFromResourceAssembler {
    private SignInCommandFromResourceAssembler() {
    }

    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.email(), resource.password());
    }
}