package com.tankiq.billing.interfaces.rest;

import com.tankiq.billing.application.commandservices.PlanCommandService;
import com.tankiq.billing.application.queryservices.PlanQueryService;
import com.tankiq.billing.domain.model.queries.GetAllPlansQuery;
import com.tankiq.billing.domain.model.queries.GetPlanByNameQuery;
import com.tankiq.billing.interfaces.rest.resources.CreatePlanResource;
import com.tankiq.billing.interfaces.rest.resources.PlanResource;
import com.tankiq.billing.interfaces.rest.transform.CreatePlanCommandFromResourceAssembler;
import com.tankiq.billing.interfaces.rest.transform.PlanResourceFromEntityAssembler;
import com.tankiq.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
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
 * Rest Controller to management plans in billing context
 * @author Retuerto Rodriguez, Jorge Manuel - Calin1407
 */
@RestController
@RequestMapping(value = "/api/v1/plans", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Plans", description = "Plan Catalog Management Endpoints")
public class PlansController {
    private final PlanQueryService planQueryService;
    private final PlanCommandService planCommandService;

    public PlansController(PlanQueryService planQueryService, PlanCommandService planCommandService) {
        this.planQueryService = planQueryService;
        this.planCommandService = planCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a new plan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plan created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid resource data"),
            @ApiResponse(responseCode = "409", description = "Conflict, Plan already exists.")
    })
    public ResponseEntity<?> createPlan(@Valid @RequestBody CreatePlanResource resource) {
        var command = CreatePlanCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = planCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                PlanResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @Operation(summary = "Get all plans", description = "Fetches all available plans in the catalog.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plans fetched successfully")
    })
    public ResponseEntity<List<PlanResource>> getAllPlans() {
        var getAllPlansQuery = new GetAllPlansQuery();
        var plans = planQueryService.handle(getAllPlansQuery);
        var planResources = plans.stream()
                .map(PlanResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(planResources);
    }

    @GetMapping("/name={name}")
    @Operation(summary = "Get plan by name", description = "Fetches a specific plan by name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan found."),
            @ApiResponse(responseCode = "400", description = "Plan not found.")
    })
    public ResponseEntity<PlanResource> getPlanByName(@PathVariable String name) {
        var getPlanByNameQuery = new GetPlanByNameQuery(name);
        var plan = planQueryService.handle(getPlanByNameQuery);

        if (plan.isEmpty()) return ResponseEntity.notFound().build();

        var planResource = PlanResourceFromEntityAssembler.toResourceFromEntity(plan.get());
        return ResponseEntity.ok(planResource);
    }
}
