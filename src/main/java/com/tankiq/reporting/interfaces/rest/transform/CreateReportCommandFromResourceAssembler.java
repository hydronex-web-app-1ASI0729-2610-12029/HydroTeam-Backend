package com.tankiq.reporting.interfaces.rest.transform;

import com.tankiq.reporting.domain.model.commands.CreateReportCommand;
import com.tankiq.reporting.interfaces.rest.resources.CreateReportResource;

public final class CreateReportCommandFromResourceAssembler {
    private CreateReportCommandFromResourceAssembler() {
    }

    public static CreateReportCommand toCommandFromResource(CreateReportResource resource) {
        return new CreateReportCommand(
                resource.periodMonth(),
                resource.periodYear(),
                resource.totalCostSoles(),
                resource.totalWaterLiters(),
                resource.generatedAt(),
                resource.buildingId(),
                resource.generatedByUserId()
        );
    }
}
