package com.tankiq.reporting.interfaces.rest;

import com.tankiq.reporting.application.commandservices.ReportCommandService;
import com.tankiq.reporting.application.queryservices.ReportQueryService;
import com.tankiq.reporting.domain.model.queries.GetAllReportsQuery;
import com.tankiq.reporting.domain.model.queries.GetReportByIdQuery;
import com.tankiq.reporting.interfaces.rest.resources.CreateReportResource;
import com.tankiq.reporting.interfaces.rest.resources.ReportResource;
import com.tankiq.reporting.interfaces.rest.transform.CreateReportCommandFromResourceAssembler;
import com.tankiq.reporting.interfaces.rest.transform.ReportResourceFromEntityAssembler;
import com.tankiq.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/reports", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reports", description = "Endpoints for managing reports")
public class ReportsController {
    private final ReportCommandService reportCommandService;
    private final ReportQueryService reportQueryService;

    public ReportsController(ReportCommandService reportCommandService, ReportQueryService reportQueryService) {
        this.reportCommandService = reportCommandService;
        this.reportQueryService = reportQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new report", description = "Creates a new report and returns the created resource.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Report created successfully", content = @Content(schema = @Schema(implementation = ReportResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> createReport(@Valid @RequestBody CreateReportResource resource) {
        var command = CreateReportCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = reportCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ReportResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @Operation(summary = "Get all reports")
    public ResponseEntity<?> getAllReports() {
        var result = reportQueryService.handle(new GetAllReportsQuery());
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                list -> list.stream().map(ReportResourceFromEntityAssembler::toResourceFromEntity).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{reportId}")
    @Operation(summary = "Get a report by id")
    public ResponseEntity<?> getReportById(@PathVariable Long reportId) {
        var result = reportQueryService.handle(new GetReportByIdQuery(reportId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ReportResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }
}
