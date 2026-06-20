package com.tankiq.billing.infrastructure.persistence.jpa.repositories;

import com.tankiq.billing.infrastructure.persistence.jpa.entities.SubscriptionPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Subscription repository to interactive with DataBase (MySQL)
 * <description>
 *     This is the operation to interactive with the database, in the table of subscriptions
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel
 */
@Repository
public interface SubscriptionPersistenceRepository extends JpaRepository<SubscriptionPersistenceEntity, Long> {
    @Query("select subscription from SubscriptionPersistenceEntity subscription where subscription.buildingId = :buildingId")
    Optional<SubscriptionPersistenceEntity> findByBuildingId(@Param("buildingId") Long buildingId);
}
