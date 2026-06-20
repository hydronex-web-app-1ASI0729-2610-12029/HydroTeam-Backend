package com.tankiq.notification.interfaces.rest;

import com.tankiq.notification.application.commandservices.AlertCommandService;
import com.tankiq.notification.application.queryservices.AlertQueryService;
import com.tankiq.notification.domain.model.queries.GetAllAlertsQuery;
import com.tankiq.notification.domain.model.queries.GetAlertByIdQuery;
import com.tankiq.notification.interfaces.rest.resources.CreateAlertResource;
import com.tankiq.notification.interfaces.rest.resources.AlertResource;
import com.tankiq.notification.interfaces.rest.transform.CreateAlertCommandFromResourceAssembler;
import com.tankiq.notification.interfaces.rest.transform.AlertResourceFromEntityAssembler;
import com.tankiq.notification.domain.model.queries.GetActiveAlertsByCisternIdQuery;
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
@RequestMapping(value = "/api/v1/alerts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Alerts", description = "Endpoints for managing alerts")
public class AlertsController {
    private final AlertCommandService alertCommandService;
    private final AlertQueryService alertQueryService;

    public AlertsController(AlertCommandService alertCommandService, AlertQueryService alertQueryService) {
        this.alertCommandService = alertCommandService;
        this.alertQueryService = alertQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new alert", description = "Creates a new alert and returns the created resource.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alert created successfully", content = @Content(schema = @Schema(implementation = AlertResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> createAlert(@Valid @RequestBody CreateAlertResource resource) {
        var command = CreateAlertCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = alertCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                AlertResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @Operation(summary = "Get all alerts")
    public ResponseEntity<?> getAllAlerts() {
        var result = alertQueryService.handle(new GetAllAlertsQuery());
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                list -> list.stream().map(AlertResourceFromEntityAssembler::toResourceFromEntity).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{alertId}")
    @Operation(summary = "Get a alert by id")
    public ResponseEntity<?> getAlertById(@PathVariable Long alertId) {
        var result = alertQueryService.handle(new GetAlertByIdQuery(alertId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                AlertResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @GetMapping("/cistern/{cisternId}/active")
    @Operation(summary = "Get active alerts by cistern id")
    public ResponseEntity<?> getActiveAlertsByCisternId(@PathVariable Long cisternId) {
        var result = alertQueryService.handle(new GetActiveAlertsByCisternIdQuery(cisternId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                list -> list.stream().map(AlertResourceFromEntityAssembler::toResourceFromEntity).toList(),
                HttpStatus.OK
        );
    }
}
