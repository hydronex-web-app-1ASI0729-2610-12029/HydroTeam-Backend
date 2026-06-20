package com.tankiq.billing.application.acl;

import com.tankiq.billing.application.commandservices.SubscriptionCommandService;
import com.tankiq.billing.application.queryservices.SubscriptionQueryService;
import com.tankiq.billing.domain.model.aggregates.Subscription;
import com.tankiq.billing.domain.model.commands.CreateSubscriptionCommand;
import com.tankiq.billing.domain.model.queries.GetSubscriptionByBuildingIdQuery;
import com.tankiq.billing.interfaces.acl.BillingContextFacade;

import java.time.LocalDate;

/**
 * Implementation of Facade
 * <description>
 * This is the Anti Corruption Layer implementation
 * Is how the bounded billing interactive with other bounded.
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel
 */
public class BillingContextFacadeImpl implements BillingContextFacade {
    private final SubscriptionCommandService subscriptionCommandService;
    private final SubscriptionQueryService subscriptionQueryService;

    public BillingContextFacadeImpl(SubscriptionCommandService subscriptionCommandService, SubscriptionQueryService subscriptionQueryService) {
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
    }

    @Override
    public Long createSubscription(LocalDate startDate, LocalDate endDate, String status, Long buildingId, Long planId) {
        var createSubscriptionCommand = new CreateSubscriptionCommand(null, startDate, endDate, status, buildingId, planId);
        var result = subscriptionCommandService.handle(createSubscriptionCommand);

        return result.toOptional()
                .map(Subscription::getId)
                .orElse(0L);
    }

    @Override
    public Long fetchSubscriptionByBuildingId(Long buildingId) {
        var getSubscriptionByBuildingIdQuery = new GetSubscriptionByBuildingIdQuery(buildingId);
        var subscription = subscriptionQueryService.handle(getSubscriptionByBuildingIdQuery);

        return subscription.isEmpty() ? 0L : subscription.get().getId();
    }
}
