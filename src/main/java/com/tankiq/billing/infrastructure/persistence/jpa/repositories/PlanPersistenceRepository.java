package com.tankiq.billing.infrastructure.persistence.jpa.repositories;

import com.tankiq.billing.infrastructure.persistence.jpa.entities.PlanPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * plan repository to interactive with DataBase (MySQL)
 * <description>
 *     This is the operation to interactive with the database, in the table of plans
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel
 */
@Repository
public interface PlanPersistenceRepository extends JpaRepository<PlanPersistenceEntity, Long> {
    @Query("select plan from PlanPersistenceEntity plan where plan.name = :name")
    Optional<PlanPersistenceEntity> findByName(@Param("name") String name);
}
