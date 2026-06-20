package com.tankiq.iam.infrastructure.persistence.jpa.adapters;

import com.tankiq.iam.domain.model.aggregates.UserBuilding;
import com.tankiq.iam.domain.repositories.UserBuildingRepository;
import com.tankiq.iam.infrastructure.persistence.jpa.assemblers.UserBuildingPersistenceAssembler;
import com.tankiq.iam.infrastructure.persistence.jpa.repositories.UserBuildingPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserBuildingRepositoryImpl implements UserBuildingRepository {

    private final UserBuildingPersistenceRepository userBuildingPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public UserBuildingRepositoryImpl(
            UserBuildingPersistenceRepository userBuildingPersistenceRepository,
            ApplicationEventPublisher eventPublisher
    ) {
        this.userBuildingPersistenceRepository = userBuildingPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<UserBuilding> findById(Long id) {
        return userBuildingPersistenceRepository.findById(id)
                .map(UserBuildingPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<UserBuilding> findAll() {
        return userBuildingPersistenceRepository.findAll()
                .stream()
                .map(UserBuildingPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public UserBuilding save(UserBuilding userBuilding) {
        boolean isNew = userBuilding.getId() == null;
        var savedEntity = userBuildingPersistenceRepository.save(
                UserBuildingPersistenceAssembler.toPersistenceFromDomain(userBuilding)
        );
        var savedUserBuilding = UserBuildingPersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedUserBuilding.onCreated();
            savedUserBuilding.domainEvents().forEach(eventPublisher::publishEvent);
            savedUserBuilding.clearDomainEvents();
        }
        return savedUserBuilding;
    }
}
