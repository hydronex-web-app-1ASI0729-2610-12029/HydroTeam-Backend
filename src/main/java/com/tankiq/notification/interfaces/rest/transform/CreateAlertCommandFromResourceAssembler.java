package com.tankiq.notification.interfaces.rest.transform;

import com.tankiq.notification.domain.model.commands.CreateAlertCommand;
import com.tankiq.notification.interfaces.rest.resources.CreateAlertResource;

public final class CreateAlertCommandFromResourceAssembler {
    private CreateAlertCommandFromResourceAssembler() {
    }

    public static CreateAlertCommand toCommandFromResource(CreateAlertResource resource) {
        return new CreateAlertCommand(
                resource.type(),
                resource.message(),
                resource.status(),
                resource.triggeredAt(),
                resource.resolvedAt(),
                resource.cisternId()
        );
    }
}
