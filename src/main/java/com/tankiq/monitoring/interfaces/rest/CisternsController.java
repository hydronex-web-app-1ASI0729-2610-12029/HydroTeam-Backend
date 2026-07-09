package com.tankiq.monitoring.interfaces.rest;

import com.tankiq.monitoring.application.commandservices.CisternCommandService;
import com.tankiq.monitoring.application.queryservices.CisternQueryService;
import com.tankiq.monitoring.domain.model.commands.DeleteCisternCommand;
import com.tankiq.monitoring.domain.model.queries.GetAllCisternsQuery;
import com.tankiq.monitoring.domain.model.queries.GetCisternByIdQuery;
import com.tankiq.monitoring.interfaces.rest.resources.CisternResource;
import com.tankiq.monitoring.interfaces.rest.resources.CreateCisternResource;
import com.tankiq.monitoring.interfaces.rest.resources.UpdateCisternResource;
import com.tankiq.monitoring.interfaces.rest.transform.CisternResourceFromEntityAssembler;
import com.tankiq.monitoring.interfaces.rest.transform.CreateCisternCommandFromResourceAssembler;
import com.tankiq.monitoring.interfaces.rest.transform.UpdateCisternCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/cisterns", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:4200"})
@Tag(name = "Cisterns", description = "Endpoints for managing cisterns")
public class CisternsController {
    private final CisternCommandService cisternCommandService;
    private final CisternQueryService cisternQueryService;

    public CisternsController(CisternCommandService cisternCommandService, CisternQueryService cisternQueryService) {
        this.cisternCommandService = cisternCommandService;
        this.cisternQueryService = cisternQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new cistern")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cistern created successfully", content = @Content(schema = @Schema(implementation = CisternResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> createCistern(@Valid @RequestBody CreateCisternResource resource) {
        var command = CreateCisternCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = cisternCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(result, CisternResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all cisterns")
    public ResponseEntity<?> getAllCisterns() {
        var result = cisternQueryService.handle(new GetAllCisternsQuery());
        return ResponseEntityAssembler.toResponseEntityFromResult(result, list -> list.stream().map(CisternResourceFromEntityAssembler::toResourceFromEntity).toList(), HttpStatus.OK);
    }

    @GetMapping("/{cisternId}")
    @Operation(summary = "Get a cistern by id")
    public ResponseEntity<?> getCisternById(@PathVariable Long cisternId) {
        var result = cisternQueryService.handle(new GetCisternByIdQuery(cisternId));
        return ResponseEntityAssembler.toResponseEntityFromResult(result, CisternResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.OK);
    }

    @PutMapping("/{cisternId}")
    @Operation(summary = "Update a cistern")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cistern updated successfully", content = @Content(schema = @Schema(implementation = CisternResource.class))),
            @ApiResponse(responseCode = "404", description = "Cistern not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> updateCistern(@PathVariable Long cisternId, @Valid @RequestBody UpdateCisternResource resource) {
        var command = UpdateCisternCommandFromResourceAssembler.toCommandFromResource(cisternId, resource);
        var result = cisternCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(result, CisternResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.OK);
    }

    @DeleteMapping("/{cisternId}")
    @Operation(summary = "Delete a cistern")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cistern deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Cistern not found")
    })
    public ResponseEntity<?> deleteCistern(@PathVariable Long cisternId) {
        var result = cisternCommandService.handle(new DeleteCisternCommand(cisternId));
        return ResponseEntityAssembler.toResponseEntityFromResult(result, ignored -> null, HttpStatus.NO_CONTENT);
    }
}
