package com.tankiq.iam.interfaces.rest.transform;

import com.tankiq.iam.domain.model.aggregates.User;
import com.tankiq.iam.interfaces.rest.resources.UserResource;

public final class UserResourceFromEntityAssembler {
    private UserResourceFromEntityAssembler() {
    }

    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getPhoneNumber()
        );
    }
}
