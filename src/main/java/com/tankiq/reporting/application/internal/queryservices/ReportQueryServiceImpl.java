package com.tankiq.reporting.application.internal.queryservices;

import com.tankiq.reporting.application.queryservices.ReportQueryService;
import com.tankiq.reporting.domain.model.aggregates.Report;
import com.tankiq.reporting.domain.model.queries.GetAllReportsQuery;
import com.tankiq.reporting.domain.model.queries.GetReportByIdQuery;
import com.tankiq.reporting.domain.repositories.ReportRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportQueryServiceImpl implements ReportQueryService {
    private final ReportRepository reportRepository;

    public ReportQueryServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Result<List<Report>, ApplicationError> handle(GetAllReportsQuery query) {
        return Result.success(reportRepository.findAll());
    }

    @Override
    public Result<Report, ApplicationError> handle(GetReportByIdQuery query) {
        return reportRepository.findById(query.reportId())
                .<Result<Report, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.validationError("Report", "Report with id '%s' was not found".formatted(query.reportId()))));
    }
}
