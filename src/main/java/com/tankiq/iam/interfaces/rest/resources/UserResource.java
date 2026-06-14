package com.tankiq.iam.interfaces.rest.resources;

public record UserResource(
        Long id,
        String name,
        String email,
        String passwordHash,
        String phoneNumber
) {
}
