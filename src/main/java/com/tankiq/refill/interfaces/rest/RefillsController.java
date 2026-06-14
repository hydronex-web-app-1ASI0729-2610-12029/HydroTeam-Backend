package com.tankiq.refill.interfaces.rest;

import com.tankiq.refill.application.commandservices.RefillCommandService;
import com.tankiq.refill.application.queryservices.RefillQueryService;
import com.tankiq.refill.domain.model.queries.GetAllRefillsQuery;
import com.tankiq.refill.domain.model.queries.GetRefillByIdQuery;
import com.tankiq.refill.interfaces.rest.resources.CreateRefillResource;
import com.tankiq.refill.interfaces.rest.resources.RefillResource;
import com.tankiq.refill.interfaces.rest.transform.CreateRefillCommandFromResourceAssembler;
import com.tankiq.refill.interfaces.rest.transform.RefillResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/refills", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Refills", description = "Endpoints for managing refills")
public class RefillsController {
    private final RefillCommandService refillCommandService;
    private final RefillQueryService refillQueryService;

    public RefillsController(RefillCommandService refillCommandService, RefillQueryService refillQueryService) {
        this.refillCommandService = refillCommandService;
        this.refillQueryService = refillQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new refill", description = "Creates a new refill and returns the created resource.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Refill created successfully", content = @Content(schema = @Schema(implementation = RefillResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> createRefill(@Valid @RequestBody CreateRefillResource resource) {
        var command = CreateRefillCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = refillCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                RefillResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @Operation(summary = "Get all refills")
    public ResponseEntity<?> getAllRefills() {
        var result = refillQueryService.handle(new GetAllRefillsQuery());
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                list -> list.stream().map(RefillResourceFromEntityAssembler::toResourceFromEntity).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{refillId}")
    @Operation(summary = "Get a refill by id")
    public ResponseEntity<?> getRefillById(@PathVariable Long refillId) {
        var result = refillQueryService.handle(new GetRefillByIdQuery(refillId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                RefillResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }
}
