package com.tankiq.iam.domain.model.aggregates;

import com.tankiq.iam.domain.model.commands.CreateUserCommand;
import com.tankiq.iam.domain.model.events.UserCreatedEvent;
import com.tankiq.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

public class User extends AbstractDomainAggregateRoot<User> {
    private Long id;
    private String name;
    private String email;
    private String passwordHash;
    private String phoneNumber;

    public User(Long id, String name, String email, String passwordHash, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
    }

    public User(String name, String email, String passwordHash, String phoneNumber) {
        this(null, name, email, passwordHash, phoneNumber);
    }

    public User(CreateUserCommand command) {
        this(command.name(), command.email(), command.passwordHash(), command.phoneNumber());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void onCreated() {
        registerDomainEvent(UserCreatedEvent.from(this));
    }
}
