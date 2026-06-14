package com.tankiq.subscription.domain.model.commands;

import java.time.LocalDate;
public record CreateSubscriptionCommand(
        LocalDate startDate,
        LocalDate endDate,
        String status,
        Long buildingId,
        Long planId
) {
}
