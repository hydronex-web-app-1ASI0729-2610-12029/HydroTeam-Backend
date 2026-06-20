package com.tankiq.monitoring.interfaces.rest;

import com.tankiq.monitoring.application.commandservices.WaterLevelReadingCommandService;
import com.tankiq.monitoring.application.queryservices.WaterLevelReadingQueryService;
import com.tankiq.monitoring.domain.model.queries.GetAllWaterLevelReadingsQuery;
import com.tankiq.monitoring.domain.model.queries.GetWaterLevelReadingByIdQuery;
import com.tankiq.monitoring.domain.model.queries.GetWaterLevelReadingsBySensorIdQuery;
import com.tankiq.monitoring.interfaces.rest.resources.CreateWaterLevelReadingResource;
import com.tankiq.monitoring.interfaces.rest.resources.WaterLevelReadingResource;
import com.tankiq.monitoring.interfaces.rest.transform.CreateWaterLevelReadingCommandFromResourceAssembler;
import com.tankiq.monitoring.interfaces.rest.transform.WaterLevelReadingResourceFromEntityAssembler;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/water-level-readings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Water Level Readings", description = "Endpoints for managing water level readings")
public class WaterLevelReadingsController {
    private final WaterLevelReadingCommandService waterLevelReadingCommandService;
    private final WaterLevelReadingQueryService waterLevelReadingQueryService;

    public WaterLevelReadingsController(WaterLevelReadingCommandService waterLevelReadingCommandService, WaterLevelReadingQueryService waterLevelReadingQueryService) {
        this.waterLevelReadingCommandService = waterLevelReadingCommandService;
        this.waterLevelReadingQueryService = waterLevelReadingQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new water level reading", description = "Creates a new water level reading and returns the created resource.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Water level reading created successfully", content = @Content(schema = @Schema(implementation = WaterLevelReadingResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> createWaterLevelReading(@Valid @RequestBody CreateWaterLevelReadingResource resource) {
        var command = CreateWaterLevelReadingCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = waterLevelReadingCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                WaterLevelReadingResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @Operation(summary = "Get all water level readings, optionally filtered by sensor")
    public ResponseEntity<?> getWaterLevelReadings(@RequestParam(required = false) Long sensorId) {
        if (sensorId != null) {
            var result = waterLevelReadingQueryService.handle(new GetWaterLevelReadingsBySensorIdQuery(sensorId));
            return ResponseEntityAssembler.toResponseEntityFromResult(
                    result,
                    list -> list.stream().map(WaterLevelReadingResourceFromEntityAssembler::toResourceFromEntity).toList(),
                    HttpStatus.OK
            );
        }
        var result = waterLevelReadingQueryService.handle(new GetAllWaterLevelReadingsQuery());
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                list -> list.stream().map(WaterLevelReadingResourceFromEntityAssembler::toResourceFromEntity).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{readingId}")
    @Operation(summary = "Get a water level reading by id")
    public ResponseEntity<?> getWaterLevelReadingById(@PathVariable Long readingId) {
        var result = waterLevelReadingQueryService.handle(new GetWaterLevelReadingByIdQuery(readingId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                WaterLevelReadingResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }
}
