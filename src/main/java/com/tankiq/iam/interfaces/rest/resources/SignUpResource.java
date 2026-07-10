package com.tankiq.iam.interfaces.rest.resources;

public record SignUpResource(String name, String email, String password, String phoneNumber, Long buildingId, String role, String apartmentNumber) {
}
