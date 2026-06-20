package com.tankiq.subscription.interfaces.rest.resources;

import java.time.LocalDate;
public record SubscriptionResource(
        Long id,
        LocalDate startDate,
        LocalDate endDate,
        String status,
        Long buildingId,
        Long planId
) {
}
