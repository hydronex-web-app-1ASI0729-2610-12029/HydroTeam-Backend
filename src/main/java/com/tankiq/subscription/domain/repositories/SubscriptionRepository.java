package com.tankiq.subscription.domain.repositories;

import com.tankiq.subscription.domain.model.aggregates.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {
    Optional<Subscription> findById(Long id);
    List<Subscription> findAll();
    Subscription save(Subscription subscription);
}
