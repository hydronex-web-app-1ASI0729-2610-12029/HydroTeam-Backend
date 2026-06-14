package com.tankiq.iam.infrastructure.persistence.jpa.repositories;

import com.tankiq.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersistenceRepository extends JpaRepository<UserPersistenceEntity, Long> {
}
