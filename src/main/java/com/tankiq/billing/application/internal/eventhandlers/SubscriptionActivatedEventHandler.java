package com.tankiq.billing.application.internal.eventhandlers;

import com.tankiq.billing.domain.model.events.SubscriptionActivatedEvent;
import com.tankiq.billing.interfaces.events.SubscriptionActivatedIntegrationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * This service is activated upon subscription activation.
 * It is currently in the testing phase and will be fully
 * operational once the frontend and backend are integrated.
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
@Service("billingSubscriptionActivatedEventHandler")
public class SubscriptionActivatedEventHandler {
    private final ApplicationEventPublisher eventPublisher;

    public SubscriptionActivatedEventHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    public void on(SubscriptionActivatedEvent event){
        eventPublisher.publishEvent(new SubscriptionActivatedIntegrationEvent(
                event.subscriptionId(),
                event.buildingId(),
                LocalDateTime.now()
        ));
    }
}

