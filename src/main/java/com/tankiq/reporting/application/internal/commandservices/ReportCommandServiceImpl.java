package com.tankiq.reporting.application.internal.commandservices;

import com.tankiq.reporting.application.commandservices.ReportCommandService;
import com.tankiq.reporting.domain.model.aggregates.Report;
import com.tankiq.reporting.domain.model.commands.CreateReportCommand;
import com.tankiq.reporting.domain.repositories.ReportRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class ReportCommandServiceImpl implements ReportCommandService {
    private final ReportRepository reportRepository;

    public ReportCommandServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Result<Report, ApplicationError> handle(CreateReportCommand command) {
        try {
            var report = new Report(command);
            var savedReport = reportRepository.save(report);
            return Result.success(savedReport);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Report", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Report creation", e.getMessage()));
        }
    }
}
