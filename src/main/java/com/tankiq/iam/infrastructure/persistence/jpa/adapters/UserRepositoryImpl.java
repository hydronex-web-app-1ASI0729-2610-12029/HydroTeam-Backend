package com.tankiq.iam.infrastructure.persistence.jpa.adapters;

import com.tankiq.iam.domain.model.aggregates.User;
import com.tankiq.iam.domain.repositories.UserRepository;
import com.tankiq.iam.infrastructure.persistence.jpa.assemblers.UserPersistenceAssembler;
import com.tankiq.iam.infrastructure.persistence.jpa.repositories.UserPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserPersistenceRepository userPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public UserRepositoryImpl(UserPersistenceRepository userPersistenceRepository, ApplicationEventPublisher eventPublisher) {
        this.userPersistenceRepository = userPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userPersistenceRepository.findById(id).map(UserPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<User> findAll() {
        return userPersistenceRepository.findAll().stream().map(UserPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public User save(User user) {
        boolean isNew = user.getId() == null;
        var savedEntity = userPersistenceRepository.save(UserPersistenceAssembler.toPersistenceFromDomain(user));
        var savedUser = UserPersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedUser.onCreated();
            savedUser.domainEvents().forEach(eventPublisher::publishEvent);
            savedUser.clearDomainEvents();
        }
        return savedUser;
    }
}
