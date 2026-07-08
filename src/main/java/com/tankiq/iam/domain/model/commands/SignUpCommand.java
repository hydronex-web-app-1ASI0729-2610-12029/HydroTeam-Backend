package com.tankiq.iam.domain.model.commands;

public record SignUpCommand(String name, String email, String password, String phoneNumber) {
}