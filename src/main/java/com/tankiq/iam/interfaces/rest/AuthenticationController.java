package com.tankiq.iam.interfaces.rest;

import com.tankiq.iam.application.commandservices.UserCommandService;
import com.tankiq.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.tankiq.iam.interfaces.rest.resources.SignInResource;
import com.tankiq.iam.interfaces.rest.resources.SignUpResource;
import com.tankiq.iam.interfaces.rest.resources.UserResource;
import com.tankiq.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.tankiq.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.tankiq.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.tankiq.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.tankiq.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication and user registration endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/sign-in")
    @Operation(summary = "User sign-in", description = "Authenticates a user with email and password and returns a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully", content = @Content(schema = @Schema(implementation = AuthenticatedUserResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid credentials"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<?> signIn(@RequestBody SignInResource resource) {
        var command = SignInCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = userCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                pair -> AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(pair.getLeft(), pair.getRight()),
                HttpStatus.OK
        );
    }

    @PostMapping("/sign-up")
    @Operation(summary = "User registration", description = "Creates a new user account with hashed password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(schema = @Schema(implementation = UserResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "Email already registered")
    })
    public ResponseEntity<?> signUp(@RequestBody SignUpResource resource) {
        var command = SignUpCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = userCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                UserResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }
}