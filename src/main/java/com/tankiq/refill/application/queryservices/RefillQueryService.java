package com.tankiq.refill.application.queryservices;

import com.tankiq.refill.domain.model.aggregates.Refill;
import com.tankiq.refill.domain.model.queries.GetAllRefillsQuery;
import com.tankiq.refill.domain.model.queries.GetRefillByIdQuery;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

import java.util.List;

public interface RefillQueryService {
    Result<List<Refill>, ApplicationError> handle(GetAllRefillsQuery query);
    Result<Refill, ApplicationError> handle(GetRefillByIdQuery query);
}
