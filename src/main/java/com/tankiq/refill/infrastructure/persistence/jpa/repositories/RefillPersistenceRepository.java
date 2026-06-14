package com.tankiq.refill.infrastructure.persistence.jpa.repositories;

import com.tankiq.refill.infrastructure.persistence.jpa.entities.RefillPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefillPersistenceRepository extends JpaRepository<RefillPersistenceEntity, Long> {
}
