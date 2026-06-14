package com.tankiq.iam.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserResource(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String passwordHash,
        @NotBlank String phoneNumber
) {
}
