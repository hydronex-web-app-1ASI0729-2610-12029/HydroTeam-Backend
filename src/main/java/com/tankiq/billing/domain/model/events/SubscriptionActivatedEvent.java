package com.tankiq.billing.domain.model.events;

import java.time.Instant;

/**
 * Event to activate a subscription
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
public record SubscriptionActivatedEvent(Long subscriptionId, Long buildingId, Instant occurredOn) {}
