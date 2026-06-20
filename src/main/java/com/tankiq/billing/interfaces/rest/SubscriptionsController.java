package com.tankiq.billing.interfaces.rest;

import com.tankiq.billing.application.commandservices.SubscriptionCommandService;
import com.tankiq.billing.application.queryservices.SubscriptionQueryService;
import com.tankiq.billing.domain.model.commands.CreateSubscriptionCommand;
import com.tankiq.billing.domain.model.queries.GetAllSubscriptionsQuery;
import com.tankiq.billing.domain.model.queries.GetSubscriptionByBuildingIdQuery;
import com.tankiq.billing.domain.model.queries.GetSubscriptionByIdQuery;
import com.tankiq.billing.interfaces.rest.resources.CreateSubscriptionResource;
import com.tankiq.billing.interfaces.rest.resources.PlanResource;
import com.tankiq.billing.interfaces.rest.resources.SubscriptionResource;
import com.tankiq.billing.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.tankiq.billing.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller to management subscriptions in billing context
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
@RestController
@RequestMapping(value = "/api/v1/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Subscriptions", description = "Subscription Management Endpoints")
public class SubscriptionsController {
    private final SubscriptionCommandService subscriptionCommandService;
    private final SubscriptionQueryService subscriptionQueryService;

    public SubscriptionsController(SubscriptionCommandService subscriptionCommandService, SubscriptionQueryService subscriptionQueryService) {
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
    }

    @PostMapping
    @Operation(
            summary = "Create a new subscription",
            description = "Creates a new building subscription with the provided details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subscription created successfully"
                    , content = @Content(schema = @Schema(implementation = SubscriptionResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid resource data"),
            @ApiResponse(responseCode = "409", description = "Conflict, Subscription already exists for this building")

    })
    public ResponseEntity<?> createSubscription(@Valid @RequestBody CreateSubscriptionResource resource) {
        var createSubscriptionCommand = CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = subscriptionCommandService.handle(createSubscriptionCommand);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SubscriptionResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @Operation(summary = "Get all subscriptions", description = "Fetches all available subscriptions.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscriptions fetched successfully")
    })
    public ResponseEntity<List<SubscriptionResource>> getAllPlans() {
        var query = new GetAllSubscriptionsQuery();
        var subscriptions = subscriptionQueryService.handle(query);

        var resources = subscriptions.stream()
                .map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/building/id={buildingId}")
    @Operation(summary = "Get subscription by building ID", description = "Fetches the subscription details associated with a building.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription found"),
            @ApiResponse(responseCode = "404", description = "Subscription not found")
    })
    public ResponseEntity<SubscriptionResource> getSubscriptionByBuildingId(@PathVariable Long buildingId) {
        var getSubscriptionByBuildingIdQuery = new GetSubscriptionByBuildingIdQuery(buildingId);
        var subscription = subscriptionQueryService.handle(getSubscriptionByBuildingIdQuery);

        return subscription.map(value -> ResponseEntity.ok(
                SubscriptionResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
