package com.tankiq.billing.application.queryservices;

import com.tankiq.billing.domain.model.aggregates.Plan;
import com.tankiq.billing.domain.model.queries.GetAllPlansQuery;
import com.tankiq.billing.domain.model.queries.GetPlanByNameQuery;

import java.util.List;
import java.util.Optional;

/**
 * Plan query Service
 * <description>
 *     Define uses cases within the business
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel - Calin 1407
 */
public interface PlanQueryService {
    List<Plan> handle(GetAllPlansQuery query);

    Optional<Plan> handle(GetPlanByNameQuery query);
}
