package com.tankiq.billing.application.internal.commandservices;

import com.tankiq.billing.application.commandservices.PlanCommandService;
import com.tankiq.billing.domain.model.aggregates.Plan;
import com.tankiq.billing.domain.model.commands.CreatePlanCommand;
import com.tankiq.billing.domain.repositories.PlanRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class PlanCommandServiceImpl implements PlanCommandService {
    private final PlanRepository planRepository;

    public PlanCommandServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public Result<Plan, ApplicationError> handle(CreatePlanCommand command) {
        try {
            if (planRepository.findByName(command.name()).isPresent()) {
                return Result.failure(ApplicationError.conflict(
                        "Plan",
                        "A plan with name '%s' already exists".formatted(command.name())
                ));
            }

            Plan plan = new Plan(
                    null,
                    command.name(),
                    command.priceSoles(),
                    command.features(),
                    command.maxSensors()
            );

            Plan savedPlan = planRepository.save(plan);
            return Result.success(savedPlan);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Plan creation", e.getMessage()));
        }
    }
}
