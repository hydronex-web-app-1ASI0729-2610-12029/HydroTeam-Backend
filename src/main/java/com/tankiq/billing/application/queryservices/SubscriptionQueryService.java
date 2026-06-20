package com.tankiq.billing.application.queryservices;

import com.tankiq.billing.domain.model.aggregates.Subscription;
import com.tankiq.billing.domain.model.queries.GetAllSubscriptionsQuery;
import com.tankiq.billing.domain.model.queries.GetSubscriptionByBuildingIdQuery;
import com.tankiq.billing.domain.model.queries.GetSubscriptionByIdQuery;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

import java.util.List;
import java.util.Optional;

/**
 * Subscription query Service
 * <description>
 *     Define uses cases within the business
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel - Calin 1407
 */
public interface SubscriptionQueryService {
    List<Subscription> handle(GetAllSubscriptionsQuery query);

    Optional<Subscription> handle(GetSubscriptionByIdQuery query);

    Optional<Subscription> handle(GetSubscriptionByBuildingIdQuery query);
}
