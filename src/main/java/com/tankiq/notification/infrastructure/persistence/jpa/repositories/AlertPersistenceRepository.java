package com.tankiq.notification.infrastructure.persistence.jpa.repositories;

import com.tankiq.notification.infrastructure.persistence.jpa.entities.AlertPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tankiq.notification.domain.model.valueobjects.AlertStatus;
import java.util.List;

@Repository
public interface AlertPersistenceRepository extends JpaRepository<AlertPersistenceEntity, Long> {
    List<AlertPersistenceEntity> findByCisternIdAndStatusNot(Long cisternId, AlertStatus status);
}