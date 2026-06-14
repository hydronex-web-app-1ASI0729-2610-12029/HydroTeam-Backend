package com.tankiq.subscription.application.internal.commandservices;

import com.tankiq.subscription.application.commandservices.SubscriptionCommandService;
import com.tankiq.subscription.domain.model.aggregates.Subscription;
import com.tankiq.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.tankiq.subscription.domain.repositories.SubscriptionRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Result<Subscription, ApplicationError> handle(CreateSubscriptionCommand command) {
        try {
            var subscription = new Subscription(command);
            var savedSubscription = subscriptionRepository.save(subscription);
            return Result.success(savedSubscription);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Subscription", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Subscription creation", e.getMessage()));
        }
    }
}
