package com.tankiq.subscription.interfaces.rest;

import com.tankiq.subscription.application.commandservices.SubscriptionCommandService;
import com.tankiq.subscription.application.queryservices.SubscriptionQueryService;
import com.tankiq.subscription.domain.model.queries.GetAllSubscriptionsQuery;
import com.tankiq.subscription.domain.model.queries.GetSubscriptionByIdQuery;
import com.tankiq.subscription.interfaces.rest.resources.CreateSubscriptionResource;
import com.tankiq.subscription.interfaces.rest.resources.SubscriptionResource;
import com.tankiq.subscription.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.tankiq.subscription.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import com.tankiq.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Subscriptions", description = "Endpoints for managing subscriptions")
public class SubscriptionsController {
    private final SubscriptionCommandService subscriptionCommandService;
    private final SubscriptionQueryService subscriptionQueryService;

    public SubscriptionsController(SubscriptionCommandService subscriptionCommandService, SubscriptionQueryService subscriptionQueryService) {
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new subscription", description = "Creates a new subscription and returns the created resource.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subscription created successfully", content = @Content(schema = @Schema(implementation = SubscriptionResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> createSubscription(@Valid @RequestBody CreateSubscriptionResource resource) {
        var command = CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = subscriptionCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SubscriptionResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @Operation(summary = "Get all subscriptions")
    public ResponseEntity<?> getAllSubscriptions() {
        var result = subscriptionQueryService.handle(new GetAllSubscriptionsQuery());
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                list -> list.stream().map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{subscriptionId}")
    @Operation(summary = "Get a subscription by id")
    public ResponseEntity<?> getSubscriptionById(@PathVariable Long subscriptionId) {
        var result = subscriptionQueryService.handle(new GetSubscriptionByIdQuery(subscriptionId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SubscriptionResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }
}
