package com.tankiq.iam.infrastructure.hashing.bcrypt;

import com.tankiq.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}