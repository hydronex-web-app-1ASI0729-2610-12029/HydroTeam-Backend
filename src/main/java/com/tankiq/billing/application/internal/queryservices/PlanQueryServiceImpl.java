package com.tankiq.billing.application.internal.queryservices;

import com.tankiq.billing.application.queryservices.PlanQueryService;
import com.tankiq.billing.domain.model.aggregates.Plan;
import com.tankiq.billing.domain.model.queries.GetAllPlansQuery;
import com.tankiq.billing.domain.model.queries.GetPlanByNameQuery;
import com.tankiq.billing.domain.repositories.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the plans query service
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
@Service
public class PlanQueryServiceImpl implements PlanQueryService {
    private final PlanRepository planRepository;

    public PlanQueryServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public List<Plan> handle(GetAllPlansQuery query) {
        return planRepository.findAll();
    }

    @Override
    public Optional<Plan> handle(GetPlanByNameQuery query) {
        return planRepository.findByName(query.name());
    }
}
