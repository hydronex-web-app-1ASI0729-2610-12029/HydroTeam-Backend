package com.tankiq.reporting.application.internal.commandservices;

import com.tankiq.refill.domain.repositories.RefillRepository;
import com.tankiq.reporting.application.commandservices.ReportCommandService;
import com.tankiq.reporting.domain.model.aggregates.Report;
import com.tankiq.reporting.domain.model.commands.CreateReportCommand;
import com.tankiq.reporting.domain.repositories.ReportRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneOffset;

@Service
public class ReportCommandServiceImpl implements ReportCommandService {
    private final ReportRepository reportRepository;
    private final RefillRepository refillRepository;

    public ReportCommandServiceImpl(ReportRepository reportRepository, RefillRepository refillRepository) {
        this.reportRepository = reportRepository;
        this.refillRepository = refillRepository;
    }

    @Override
    public Result<Report, ApplicationError> handle(CreateReportCommand command) {
        try {
            if (reportAlreadyExists(command)) {
                return Result.failure(ApplicationError.conflict(
                        "Report",
                        "A report already exists for building %d in %02d/%d"
                                .formatted(command.buildingId(), command.periodMonth(), command.periodYear())
                ));
            }

            var reportPeriod = YearMonth.of(command.periodYear(), command.periodMonth());
            var periodStart = reportPeriod
                    .atDay(1)
                    .atStartOfDay()
                    .toInstant(ZoneOffset.UTC);
            var periodEnd = reportPeriod
                    .plusMonths(1)
                    .atDay(1)
                    .atStartOfDay()
                    .toInstant(ZoneOffset.UTC);

            var refills = refillRepository.findByBuildingIdAndRefillDateBetween(
                    command.buildingId(),
                    periodStart,
                    periodEnd
            );

            if (refills.isEmpty()) {
                return Result.failure(ApplicationError.validationError(
                        "Report",
                        "At least one refill is required to generate a monthly report"
                ));
            }

            var totalCostSoles = refills.stream()
                    .mapToDouble(refill -> refill.getCostSoles() == null ? 0.0 : refill.getCostSoles())
                    .sum();
            var totalWaterLiters = refills.stream()
                    .mapToDouble(refill -> refill.getLiters() == null ? 0.0 : refill.getLiters())
                    .sum();

            var report = new Report(
                    command.periodMonth(),
                    command.periodYear(),
                    totalCostSoles,
                    totalWaterLiters,
                    Instant.now(),
                    command.buildingId(),
                    command.generatedByUserId()
            );
            var savedReport = reportRepository.save(report);
            return Result.success(savedReport);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Report", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Report creation", e.getMessage()));
        }
    }

    private boolean reportAlreadyExists(CreateReportCommand command) {
        return reportRepository.findByBuildingId(command.buildingId())
                .stream()
                .anyMatch(report -> report.getPeriodMonth().equals(command.periodMonth())
                        && report.getPeriodYear().equals(command.periodYear()));
    }
}
