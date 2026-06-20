package com.tankiq.billing.domain.repositories;

import com.tankiq.billing.domain.model.aggregates.Plan;

import java.util.List;
import java.util.Optional;

/**
 * Plan repository
 * <description>
 *     This interface is the contract for data access operation related to plans.
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
public interface PlanRepository {
    Plan save(Plan plan);

    List<Plan> findAll();

    Optional<Plan> findById(Long id);

    Optional<Plan> findByName(String name);
}
