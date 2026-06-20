package com.tankiq.billing.application.commandservices;

import com.tankiq.billing.domain.model.aggregates.Subscription;
import com.tankiq.billing.domain.model.commands.CreateSubscriptionCommand;
import com.tankiq.billing.domain.model.commands.UpdateSubscriptionCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

/**
 * Subscription Command Service
 * <description>
 *     Define uses cases within the business
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel - Calin 1407
 */
public interface SubscriptionCommandService {
    Result<Subscription, ApplicationError> handle(CreateSubscriptionCommand command);

    Result<Subscription, ApplicationError> handle(UpdateSubscriptionCommand command);
}
