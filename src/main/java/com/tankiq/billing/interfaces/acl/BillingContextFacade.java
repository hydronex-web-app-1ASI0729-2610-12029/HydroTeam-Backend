package com.tankiq.billing.interfaces.acl;

import java.time.LocalDate;

/**
 * Billing context facade
 * <description>
 *     This interface is the contract of the facade
 *     Allowed function to other bounded interactive.
 * </description>
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
public interface BillingContextFacade {
    Long createSubscription(LocalDate startDate, LocalDate endDate, String status, Long buildingId, Long planId);

    Long fetchSubscriptionByBuildingId(Long buildingId);
}
