package com.tankiq.subscription.application.internal.queryservices;

import com.tankiq.subscription.application.queryservices.SubscriptionQueryService;
import com.tankiq.subscription.domain.model.aggregates.Subscription;
import com.tankiq.subscription.domain.model.queries.GetAllSubscriptionsQuery;
import com.tankiq.subscription.domain.model.queries.GetSubscriptionByIdQuery;
import com.tankiq.subscription.domain.repositories.SubscriptionRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Result<List<Subscription>, ApplicationError> handle(GetAllSubscriptionsQuery query) {
        return Result.success(subscriptionRepository.findAll());
    }

    @Override
    public Result<Subscription, ApplicationError> handle(GetSubscriptionByIdQuery query) {
        return subscriptionRepository.findById(query.subscriptionId())
                .<Result<Subscription, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.validationError("Subscription", "Subscription with id '%s' was not found".formatted(query.subscriptionId()))));
    }
}
