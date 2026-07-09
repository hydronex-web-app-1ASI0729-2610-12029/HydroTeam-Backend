package com.tankiq.notification.interfaces.rest;

import com.tankiq.notification.application.commandservices.AlertCommandService;
import com.tankiq.notification.application.queryservices.AlertQueryService;
import com.tankiq.notification.domain.model.queries.GetAllAlertsQuery;
import com.tankiq.notification.domain.model.queries.GetAlertByIdQuery;
import com.tankiq.notification.domain.model.queries.GetActiveAlertsByCisternIdQuery;
import com.tankiq.notification.domain.repositories.AlertRepository;
import com.tankiq.notification.interfaces.rest.resources.CreateAlertResource;
import com.tankiq.notification.interfaces.rest.resources.UpdateAlertResource;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/alerts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Alerts", description = "Endpoints for managing alerts")
public class AlertsController {
    private final AlertCommandService alertCommandService;
    private final AlertQueryService alertQueryService;
    private final AlertRepository alertRepository;

    public AlertsController(AlertCommandService alertCommandService, AlertQueryService alertQueryService, AlertRepository alertRepository) {
        this.alertCommandService = alertCommandService;
        this.alertQueryService = alertQueryService;
        this.alertRepository = alertRepository;
    }

    @PostMapping
    @Operation(summary = "Create a new alert")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alert created successfully", content = @Content(schema = @Schema(implementation = AlertResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> createAlert(@Valid @RequestBody CreateAlertResource resource) {
        var command = CreateAlertCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = alertCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(result, AlertResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED);
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
    @Operation(summary = "Get an alert by id")
    public ResponseEntity<?> getAlertById(@PathVariable Long alertId) {
        var result = alertQueryService.handle(new GetAlertByIdQuery(alertId));
        return ResponseEntityAssembler.toResponseEntityFromResult(result, AlertResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.OK);
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

    @PutMapping("/{alertId}")
    @Operation(summary = "Update an alert")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alert updated successfully"),
            @ApiResponse(responseCode = "404", description = "Alert not found")
    })
    public ResponseEntity<?> updateAlert(@PathVariable Long alertId, @Valid @RequestBody UpdateAlertResource resource) {
        var existing = alertRepository.findById(alertId);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        var alert = existing.get();
        alert.setStatus(resource.status());
        if (resource.resolvedAt() != null) alert.setResolvedAt(resource.resolvedAt());

        var saved = alertRepository.save(alert);
        return ResponseEntity.ok(AlertResourceFromEntityAssembler.toResourceFromEntity(saved));
    }

    @DeleteMapping("/{alertId}")
    @Operation(summary = "Delete an alert")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Alert deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Alert not found")
    })
    public ResponseEntity<?> deleteAlert(@PathVariable Long alertId) {
        if (!alertRepository.existsById(alertId)) return ResponseEntity.notFound().build();
        alertRepository.deleteById(alertId);
        return ResponseEntity.noContent().build();
    }
}