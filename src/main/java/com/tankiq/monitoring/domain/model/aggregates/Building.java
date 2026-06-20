package com.tankiq.monitoring.domain.model.aggregates;

import com.tankiq.monitoring.domain.model.commands.CreateBuildingCommand;
import com.tankiq.monitoring.domain.model.events.BuildingCreatedEvent;
import com.tankiq.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

public class Building extends AbstractDomainAggregateRoot<Building> {
    private Long id;
    private String name;
    private String address;
    private String district;

    public Building(Long id, String name, String address, String district) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.district = district;
    }

    public Building(String name, String address, String district) {
        this(null, name, address, district);
    }

    public Building(CreateBuildingCommand command) {
        this(command.name(), command.address(), command.district());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getDistrict() { return district; }

    public void onCreated() {
        registerDomainEvent(BuildingCreatedEvent.from(this));
    }
}
