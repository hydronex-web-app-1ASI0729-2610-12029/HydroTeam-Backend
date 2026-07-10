package com.tankiq.iam.application.internal.outboundservices.security;

public interface PasswordBreachCheckService {
    boolean isPasswordBreached(String rawPassword);
}
