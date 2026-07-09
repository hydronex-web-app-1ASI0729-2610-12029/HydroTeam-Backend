package com.tankiq.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String name, String email, String phoneNumber, String token) {
}