package com.tankiq.billing.domain.model.commands;

import java.time.LocalDate;

/**
 * Command to update subscription.
 * This is not enabled for the moment.
 * @author Retuerto Rodriguez Jorge Manuel - Calin1407
 */
public record UpdateSubscriptionCommand (Long id,
                                         LocalDate startDate,
                                         LocalDate endDate,
                                         String status,
                                         Long buildingId,
                                         Long planId) {}
