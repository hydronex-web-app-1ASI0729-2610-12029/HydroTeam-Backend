package com.tankiq.reporting.application.commandservices;

import com.tankiq.reporting.domain.model.aggregates.Report;
import com.tankiq.reporting.domain.model.commands.CreateReportCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

public interface ReportCommandService {
    Result<Report, ApplicationError> handle(CreateReportCommand command);
}
