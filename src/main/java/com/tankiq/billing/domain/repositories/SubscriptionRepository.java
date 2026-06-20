package com.tankiq.billing.domain.repositories;

import com.tankiq.billing.domain.model.aggregates.Subscription;

import java.util.List;
import java.util.Optional;

/**
 * Subscription repository
 * <description>
 *     This interface is the contract for data access operation related to subscription.
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
public interface SubscriptionRepository {
    Subscription save(Subscription subscription);

    List<Subscription> findAll();

    Optional<Subscription> findById(Long id);

    Optional<Subscription> findByBuildingId(Long buildingId);
}
