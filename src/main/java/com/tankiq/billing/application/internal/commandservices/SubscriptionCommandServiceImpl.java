package com.tankiq.billing.application.internal.commandservices;

import com.tankiq.billing.application.commandservices.SubscriptionCommandService;
import com.tankiq.billing.domain.model.aggregates.Subscription;
import com.tankiq.billing.domain.model.commands.CreateSubscriptionCommand;
import com.tankiq.billing.domain.model.commands.UpdateSubscriptionCommand;
import com.tankiq.billing.domain.model.valueobjects.SubscriptionStatus;
import com.tankiq.billing.domain.repositories.SubscriptionRepository;
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
            if (subscriptionRepository.findByBuildingId(command.buildingId()).isPresent()) {
                return Result.failure(ApplicationError.conflict(
                        "Subscription",
                        "A subscription for building id '%s' already exists".formatted(command.buildingId())
                ));
            }

            SubscriptionStatus status = SubscriptionStatus.valueOf(command.status().toUpperCase());

            Subscription subscription = new Subscription(
                    command.id(),
                    command.startDate(),
                    command.endDate(),
                    status,
                    command.buildingId(),
                    command.planId()
            );
            Subscription savedSubscription = subscriptionRepository.save(subscription);

            return Result.success(savedSubscription);

        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Subscription", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Subscription creation", e.getMessage()));
        }
    }

    @Override
    public Result<Subscription, ApplicationError> handle(UpdateSubscriptionCommand command){
        try {
            var existingSubscriptionOptional = subscriptionRepository.findById(command.id());
            if (existingSubscriptionOptional.isEmpty()) {
                return Result.failure(ApplicationError.businessRuleViolation(
                        "SubscriptionNotFound",
                        "Subscription with ID '%s' does not exist".formatted(command.id())
                ));
            }
            SubscriptionStatus status = SubscriptionStatus.valueOf(command.status().toUpperCase());

            Subscription subscriptionToUpdate = new Subscription(
                    command.id(),
                    command.startDate(),
                    command.endDate(),
                    status,
                    command.buildingId(),
                    command.planId()
            );

            Subscription updatedSubscription = subscriptionRepository.save(subscriptionToUpdate);
            return Result.success(updatedSubscription);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Subscription", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Subscription update", e.getMessage()));
        }
    }
}
