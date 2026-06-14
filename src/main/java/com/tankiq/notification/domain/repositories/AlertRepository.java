package com.tankiq.notification.domain.repositories;

import com.tankiq.notification.domain.model.aggregates.Alert;

import java.util.List;
import java.util.Optional;

public interface AlertRepository {
    Optional<Alert> findById(Long id);
    List<Alert> findAll();
    Alert save(Alert alert);
}
