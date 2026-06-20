package com.tankiq.reporting.domain.repositories;

import com.tankiq.reporting.domain.model.aggregates.Report;

import java.util.List;
import java.util.Optional;

public interface ReportRepository {
    Optional<Report> findById(Long id);
    List<Report> findAll();
    List<Report> findByBuildingId(Long buildingId);
    Report save(Report report);
}
