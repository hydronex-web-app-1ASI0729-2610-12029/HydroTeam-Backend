package com.tankiq.billing.application.internal.queryservices;

import com.tankiq.billing.application.queryservices.SubscriptionQueryService;
import com.tankiq.billing.domain.model.aggregates.Subscription;
import com.tankiq.billing.domain.model.queries.GetAllSubscriptionsQuery;
import com.tankiq.billing.domain.model.queries.GetSubscriptionByBuildingIdQuery;
import com.tankiq.billing.domain.model.queries.GetSubscriptionByIdQuery;
import com.tankiq.billing.domain.repositories.SubscriptionRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the subscription query service
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<Subscription> handle(GetAllSubscriptionsQuery query) {
        return subscriptionRepository.findAll();
    }

    @Override
    public Optional<Subscription> handle(GetSubscriptionByIdQuery query) {
        return subscriptionRepository.findById(query.subscriptionId());
    }

    @Override
    public Optional<Subscription> handle(GetSubscriptionByBuildingIdQuery query) {
        return subscriptionRepository.findByBuildingId(query.buildingId());
    }
}
