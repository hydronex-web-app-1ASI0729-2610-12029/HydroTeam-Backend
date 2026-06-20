package com.tankiq.iam.interfaces.rest;

import com.tankiq.iam.application.commandservices.UserBuildingCommandService;
import com.tankiq.iam.application.queryservices.UserBuildingQueryService;
import com.tankiq.iam.domain.model.queries.GetAllUserBuildingsQuery;
import com.tankiq.iam.domain.model.queries.GetUserBuildingByIdQuery;
import com.tankiq.iam.interfaces.rest.resources.CreateUserBuildingResource;
import com.tankiq.iam.interfaces.rest.resources.UserBuildingResource;
import com.tankiq.iam.interfaces.rest.transform.CreateUserBuildingCommandFromResourceAssembler;
import com.tankiq.iam.interfaces.rest.transform.UserBuildingResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/user-buildings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "UserBuildings", description = "Endpoints for managing user-building relationships")
public class UserBuildingsController {

    private final UserBuildingCommandService userBuildingCommandService;
    private final UserBuildingQueryService userBuildingQueryService;

    public UserBuildingsController(
            UserBuildingCommandService userBuildingCommandService,
            UserBuildingQueryService userBuildingQueryService
    ) {
        this.userBuildingCommandService = userBuildingCommandService;
        this.userBuildingQueryService = userBuildingQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new user-building relationship",
            description = "Creates a new relationship between a user and a building.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "UserBuilding created successfully",
                    content = @Content(schema = @Schema(implementation = UserBuildingResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> createUserBuilding(@Valid @RequestBody CreateUserBuildingResource resource) {
        var command = CreateUserBuildingCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = userBuildingCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                UserBuildingResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @Operation(summary = "Get all user-building relationships")
    public ResponseEntity<?> getAllUserBuildings() {
        var result = userBuildingQueryService.handle(new GetAllUserBuildingsQuery());
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                list -> list.stream().map(UserBuildingResourceFromEntityAssembler::toResourceFromEntity).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{userBuildingId}")
    @Operation(summary = "Get a user-building relationship by id")
    public ResponseEntity<?> getUserBuildingById(@PathVariable Long userBuildingId) {
        var result = userBuildingQueryService.handle(new GetUserBuildingByIdQuery(userBuildingId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                UserBuildingResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }
}
