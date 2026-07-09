package com.tankiq.monitoring.interfaces.rest;

import com.tankiq.monitoring.application.commandservices.BuildingCommandService;
import com.tankiq.monitoring.application.queryservices.BuildingQueryService;
import com.tankiq.monitoring.domain.model.commands.DeleteBuildingCommand;
import com.tankiq.monitoring.domain.model.queries.GetAllBuildingsQuery;
import com.tankiq.monitoring.domain.model.queries.GetBuildingByIdQuery;
import com.tankiq.monitoring.interfaces.rest.resources.BuildingResource;
import com.tankiq.monitoring.interfaces.rest.resources.CreateBuildingResource;
import com.tankiq.monitoring.interfaces.rest.resources.UpdateBuildingResource;
import com.tankiq.monitoring.interfaces.rest.transform.BuildingResourceFromEntityAssembler;
import com.tankiq.monitoring.interfaces.rest.transform.CreateBuildingCommandFromResourceAssembler;
import com.tankiq.monitoring.interfaces.rest.transform.UpdateBuildingCommandFromResourceAssembler;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/buildings", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:4200"})
@Tag(name = "Buildings", description = "Endpoints for managing buildings")
public class BuildingsController {
    private final BuildingCommandService buildingCommandService;
    private final BuildingQueryService buildingQueryService;

    public BuildingsController(BuildingCommandService buildingCommandService, BuildingQueryService buildingQueryService) {
        this.buildingCommandService = buildingCommandService;
        this.buildingQueryService = buildingQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new building")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Building created successfully", content = @Content(schema = @Schema(implementation = BuildingResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> createBuilding(@Valid @RequestBody CreateBuildingResource resource) {
        var command = CreateBuildingCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = buildingCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(result, BuildingResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all buildings")
    public ResponseEntity<?> getAllBuildings() {
        var result = buildingQueryService.handle(new GetAllBuildingsQuery());
        return ResponseEntityAssembler.toResponseEntityFromResult(result, list -> list.stream().map(BuildingResourceFromEntityAssembler::toResourceFromEntity).toList(), HttpStatus.OK);
    }

    @GetMapping("/{buildingId}")
    @Operation(summary = "Get a building by id")
    public ResponseEntity<?> getBuildingById(@PathVariable Long buildingId) {
        var result = buildingQueryService.handle(new GetBuildingByIdQuery(buildingId));
        return ResponseEntityAssembler.toResponseEntityFromResult(result, BuildingResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.OK);
    }

    @PutMapping("/{buildingId}")
    @Operation(summary = "Update a building")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Building updated successfully", content = @Content(schema = @Schema(implementation = BuildingResource.class))),
            @ApiResponse(responseCode = "404", description = "Building not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> updateBuilding(@PathVariable Long buildingId, @Valid @RequestBody UpdateBuildingResource resource) {
        var command = UpdateBuildingCommandFromResourceAssembler.toCommandFromResource(buildingId, resource);
        var result = buildingCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(result, BuildingResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.OK);
    }

    @DeleteMapping("/{buildingId}")
    @Operation(summary = "Delete a building")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Building deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Building not found")
    })
    public ResponseEntity<?> deleteBuilding(@PathVariable Long buildingId) {
        var result = buildingCommandService.handle(new DeleteBuildingCommand(buildingId));
        return ResponseEntityAssembler.toResponseEntityFromResult(result, ignored -> null, HttpStatus.NO_CONTENT);
    }
}
