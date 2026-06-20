package com.tankiq.monitoring.interfaces.rest;

import com.tankiq.monitoring.application.commandservices.SensorCommandService;
import com.tankiq.monitoring.application.queryservices.SensorQueryService;
import com.tankiq.monitoring.domain.model.queries.GetAllSensorsQuery;
import com.tankiq.monitoring.domain.model.queries.GetSensorByIdQuery;
import com.tankiq.monitoring.domain.model.queries.GetSensorsByCisternIdQuery;
import com.tankiq.monitoring.interfaces.rest.resources.CreateSensorResource;
import com.tankiq.monitoring.interfaces.rest.resources.SensorResource;
import com.tankiq.monitoring.interfaces.rest.transform.CreateSensorCommandFromResourceAssembler;
import com.tankiq.monitoring.interfaces.rest.transform.SensorResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/sensors", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sensors", description = "Endpoints for managing sensors")
public class SensorsController {
    private final SensorCommandService sensorCommandService;
    private final SensorQueryService sensorQueryService;

    public SensorsController(SensorCommandService sensorCommandService, SensorQueryService sensorQueryService) {
        this.sensorCommandService = sensorCommandService;
        this.sensorQueryService = sensorQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new sensor", description = "Creates a new sensor and returns the created resource.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sensor created successfully", content = @Content(schema = @Schema(implementation = SensorResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> createSensor(@Valid @RequestBody CreateSensorResource resource) {
        var command = CreateSensorCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = sensorCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SensorResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @Operation(summary = "Get all sensors, optionally filtered by cistern")
    public ResponseEntity<?> getSensors(@RequestParam(required = false) Long cisternId) {
        if (cisternId != null) {
            var result = sensorQueryService.handle(new GetSensorsByCisternIdQuery(cisternId));
            return ResponseEntityAssembler.toResponseEntityFromResult(
                    result,
                    list -> list.stream().map(SensorResourceFromEntityAssembler::toResourceFromEntity).toList(),
                    HttpStatus.OK
            );
        }
        var result = sensorQueryService.handle(new GetAllSensorsQuery());
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                list -> list.stream().map(SensorResourceFromEntityAssembler::toResourceFromEntity).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{sensorId}")
    @Operation(summary = "Get a sensor by id")
    public ResponseEntity<?> getSensorById(@PathVariable Long sensorId) {
        var result = sensorQueryService.handle(new GetSensorByIdQuery(sensorId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SensorResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }
}
