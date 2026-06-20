package com.tankiq.billing.domain.model.aggregates;

import com.tankiq.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

/**
 * Subscription Plans
 * <description>
 *     Represents the type of subscription plan of the platform.
 * </description>
 * @author Retuerto Rodriguez Jorge Manuel - Calin1407
 */
public class Plan extends AbstractDomainAggregateRoot<Plan> {
    @Getter
    @Setter
    private Long id;

    @Getter
    private String name;

    @Getter
    private Double priceSoles;

    @Getter
    private String features;

    @Getter
    private Integer maxSensors;

    /**
     * Constructor plan without parameters.
     */
    public Plan() {}

    /**
     * Constructor plan with parameters.
     */
    public Plan(Long id, String name, Double priceSoles
            , String features, Integer maxSensors){
        this.id = id;
        this.name = name;
        this.priceSoles = priceSoles;
        this.features = features;
        this.maxSensors = maxSensors;
    }
}
