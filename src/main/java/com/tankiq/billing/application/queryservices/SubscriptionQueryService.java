package com.tankiq.subscription.application.queryservices;

import com.tankiq.subscription.domain.model.aggregates.Subscription;
import com.tankiq.subscription.domain.model.queries.GetAllSubscriptionsQuery;
import com.tankiq.subscription.domain.model.queries.GetSubscriptionByIdQuery;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

import java.util.List;

public interface SubscriptionQueryService {
    Result<List<Subscription>, ApplicationError> handle(GetAllSubscriptionsQuery query);
    Result<Subscription, ApplicationError> handle(GetSubscriptionByIdQuery query);
}
