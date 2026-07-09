package com.tankiq.iam.interfaces.rest.transform;

import com.tankiq.iam.domain.model.aggregates.User;
import com.tankiq.iam.interfaces.rest.resources.AuthenticatedUserResource;

public final class AuthenticatedUserResourceFromEntityAssembler {
    private AuthenticatedUserResourceFromEntityAssembler() {
    }

    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), token);
    }
}