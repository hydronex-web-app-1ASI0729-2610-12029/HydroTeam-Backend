package com.tankiq.billing.application.commandservices;

import com.tankiq.billing.domain.model.aggregates.Plan;
import com.tankiq.billing.domain.model.commands.CreatePlanCommand;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

/**
 * Plan Command Service
 * <description>
 *     Define uses cases within the business
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel - Calin 1407
 */
public interface PlanCommandService {
    Result<Plan, ApplicationError> handle(CreatePlanCommand command);
}
