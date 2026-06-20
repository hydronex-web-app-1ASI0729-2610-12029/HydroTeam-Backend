package com.tankiq.billing.domain.model.commands;

import java.time.LocalDate;

/**
 * Command to create new subscription
 * @author Retuerto Rodriguez Jorge Manuel - Calin1407
 */
public record CreateSubscriptionCommand(Long id,
                                        LocalDate startDate,
                                        LocalDate endDate,
                                        String status,
                                        Long buildingId,
                                        Long planId) {}
