package com.tankiq.reporting.application.queryservices;

import com.tankiq.reporting.domain.model.aggregates.Report;
import com.tankiq.reporting.domain.model.queries.GetAllReportsQuery;
import com.tankiq.reporting.domain.model.queries.GetReportByIdQuery;
import com.tankiq.reporting.domain.model.queries.GetReportsByBuildingIdQuery;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

import java.util.List;

public interface ReportQueryService {
    Result<List<Report>, ApplicationError> handle(GetAllReportsQuery query);
    Result<Report, ApplicationError> handle(GetReportByIdQuery query);
    Result<List<Report>, ApplicationError> handle(GetReportsByBuildingIdQuery query);
}
