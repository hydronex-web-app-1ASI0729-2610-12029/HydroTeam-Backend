package com.tankiq.refill.infrastructure.persistence.jpa.assemblers;

import com.tankiq.refill.domain.model.aggregates.Refill;
import com.tankiq.refill.infrastructure.persistence.jpa.entities.RefillPersistenceEntity;

public final class RefillPersistenceAssembler {
    private RefillPersistenceAssembler() {
    }

    public static Refill toDomainFromPersistence(RefillPersistenceEntity entity) {
        return new Refill(
                entity.getId(),
                entity.getRefillDate(),
                entity.getLiters(),
                entity.getCostSoles(),
                entity.getSupplierName(),
                entity.getInvoiceNumber(),
                entity.getBuildingId(),
                entity.getRegisteredByUserId()
        );
    }

    public static RefillPersistenceEntity toPersistenceFromDomain(Refill refill) {
        var entity = new RefillPersistenceEntity();
        entity.setId(refill.getId());
        entity.setRefillDate(refill.getRefillDate());
        entity.setLiters(refill.getLiters());
        entity.setCostSoles(refill.getCostSoles());
        entity.setSupplierName(refill.getSupplierName());
        entity.setInvoiceNumber(refill.getInvoiceNumber());
        entity.setBuildingId(refill.getBuildingId());
        entity.setRegisteredByUserId(refill.getRegisteredByUserId());
        return entity;
    }
}
