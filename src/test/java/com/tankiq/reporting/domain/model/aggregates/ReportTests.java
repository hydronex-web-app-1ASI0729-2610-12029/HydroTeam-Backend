package com.tankiq.reporting.domain.model.aggregates;

import com.tankiq.reporting.domain.model.commands.CreateReportCommand;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReportTests {

    @Test
    void createsReportWithValidMonthlyMetrics() {
        var command = new CreateReportCommand(6, 2026, 1L, 1L);

        assertDoesNotThrow(() -> new Report(command));
    }

    @Test
    void rejectsInvalidMonth() {
        var command = new CreateReportCommand(13, 2026, 1L, 1L);

        assertThrows(IllegalArgumentException.class, () -> new Report(command));
    }

    @Test
    void rejectsNegativeTotals() {
        assertThrows(IllegalArgumentException.class, () -> new Report(6, 2026, -1.0, 8400.0, Instant.now(), 1L, 1L));
    }
}
