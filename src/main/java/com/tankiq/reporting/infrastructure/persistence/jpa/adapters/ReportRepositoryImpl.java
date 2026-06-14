package com.tankiq.reporting.infrastructure.persistence.jpa.adapters;

import com.tankiq.reporting.domain.model.aggregates.Report;
import com.tankiq.reporting.domain.repositories.ReportRepository;
import com.tankiq.reporting.infrastructure.persistence.jpa.assemblers.ReportPersistenceAssembler;
import com.tankiq.reporting.infrastructure.persistence.jpa.repositories.ReportPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReportRepositoryImpl implements ReportRepository {
    private final ReportPersistenceRepository reportPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ReportRepositoryImpl(ReportPersistenceRepository reportPersistenceRepository, ApplicationEventPublisher eventPublisher) {
        this.reportPersistenceRepository = reportPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Report> findById(Long id) {
        return reportPersistenceRepository.findById(id).map(ReportPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Report> findAll() {
        return reportPersistenceRepository.findAll().stream().map(ReportPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public Report save(Report report) {
        boolean isNew = report.getId() == null;
        var savedEntity = reportPersistenceRepository.save(ReportPersistenceAssembler.toPersistenceFromDomain(report));
        var savedReport = ReportPersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedReport.onCreated();
            savedReport.domainEvents().forEach(eventPublisher::publishEvent);
            savedReport.clearDomainEvents();
        }
        return savedReport;
    }
}
