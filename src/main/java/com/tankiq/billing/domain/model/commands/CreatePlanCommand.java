package com.tankiq.billing.domain.model.commands;

/**
 * Command to create new plan
 * @author Retuerto Rodriguez Jorge Manuel - Calin1407
 */
public record CreatePlanCommand(String name,
                                Double priceSoles,
                                String features,
                                Integer maxSensors) {
}
