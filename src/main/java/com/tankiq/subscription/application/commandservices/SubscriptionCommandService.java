package com.tankiq.subscription.application.commandservices;

import com.tankiq.subscription.domain.model.aggregates.Subscription;
import com.tankiq.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

public interface SubscriptionCommandService {
    Result<Subscription, ApplicationError> handle(CreateSubscriptionCommand command);
}
