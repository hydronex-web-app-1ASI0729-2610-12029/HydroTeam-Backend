package com.tankiq.billing.domain.model.aggregates;

import com.tankiq.billing.domain.model.valueobjects.SubscriptionStatus;
import com.tankiq.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 *  Subscription associated by user
 *  <description>
 *      Represents the subscription associated by user.
 *      The user only have to have a subscription.
 *  </description>
 * @author Retuerto Rodriguez Jorge Manuel - Calin1407
 */
public class Subscription extends AbstractDomainAggregateRoot<Subscription>{
    @Getter
    @Setter
    private Long id;

    @Getter
    private LocalDate startDate;

    @Getter
    private LocalDate endDate;

    @Getter
    private SubscriptionStatus status;

    @Getter
    private Long buildingId;

    @Getter
    private Long planId;

    public Subscription() {}

    /**
     * Constructor of Subscription.
     */
    public Subscription(Long id, LocalDate startDate, LocalDate endDate
            , SubscriptionStatus status, Long buildingId, Long planId){
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.buildingId = buildingId;
        this.planId = planId;
    }

    /**
     * Change status to Canceled
     */
    public void cancel() {
        this.status = SubscriptionStatus.CANCELED;
    }

    /**
     * Change status to Expired
     */
    public void expire() {
        this.status = SubscriptionStatus.EXPIRED;
    }
}