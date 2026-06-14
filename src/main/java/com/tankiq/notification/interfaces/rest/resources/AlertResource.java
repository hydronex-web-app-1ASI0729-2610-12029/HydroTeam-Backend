package com.tankiq.notification.interfaces.rest.resources;

import java.time.Instant;
public record AlertResource(
        Long id,
        String type,
        String message,
        String status,
        Instant triggeredAt,
        Instant resolvedAt,
        Long cisternId
) {
}
