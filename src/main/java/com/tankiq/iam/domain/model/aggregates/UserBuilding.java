package com.tankiq.iam.domain.model.aggregates;

import com.tankiq.iam.domain.model.commands.CreateUserBuildingCommand;
import com.tankiq.iam.domain.model.events.UserBuildingCreatedEvent;
import com.tankiq.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

public class UserBuilding extends AbstractDomainAggregateRoot<UserBuilding> {

    private Long id;
    private Long userId;
    private Long buildingId;
    private String role;
    private String apartmentNumber;

    public UserBuilding(Long id, Long userId, Long buildingId, String role, String apartmentNumber) {
        this.id = id;
        this.userId = userId;
        this.buildingId = buildingId;
        this.role = role;
        this.apartmentNumber = apartmentNumber;
    }

    public UserBuilding(Long userId, Long buildingId, String role, String apartmentNumber) {
        this(null, userId, buildingId, role, apartmentNumber);
    }

    public UserBuilding(CreateUserBuildingCommand command) {
        this(command.userId(), command.buildingId(), command.role(), command.apartmentNumber());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public Long getBuildingId() { return buildingId; }
    public String getRole() { return role; }
    public String getApartmentNumber() { return apartmentNumber; }

    public void onCreated() {
        registerDomainEvent(UserBuildingCreatedEvent.from(this));
    }
}
