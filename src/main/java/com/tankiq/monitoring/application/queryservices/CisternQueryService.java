package com.tankiq.monitoring.application.queryservices;

import com.tankiq.monitoring.domain.model.aggregates.Cistern;
import com.tankiq.monitoring.domain.model.queries.GetAllCisternsQuery;
import com.tankiq.monitoring.domain.model.queries.GetCisternByIdQuery;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

import java.util.List;

public interface CisternQueryService {
    Result<List<Cistern>, ApplicationError> handle(GetAllCisternsQuery query);
    Result<Cistern, ApplicationError> handle(GetCisternByIdQuery query);
}
