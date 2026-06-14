package com.tankiq.notification.interfaces.rest.transform;

import com.tankiq.notification.domain.model.aggregates.Alert;
import com.tankiq.notification.interfaces.rest.resources.AlertResource;

public final class AlertResourceFromEntityAssembler {
    private AlertResourceFromEntityAssembler() {
    }

    public static AlertResource toResourceFromEntity(Alert alert) {
        return new AlertResource(
                alert.getId(),
                alert.getType(),
                alert.getMessage(),
                alert.getStatus(),
                alert.getTriggeredAt(),
                alert.getResolvedAt(),
                alert.getCisternId()
        );
    }
}
