package com.tankiq.refill.application.internal.queryservices;

import com.tankiq.refill.application.queryservices.RefillQueryService;
import com.tankiq.refill.domain.model.aggregates.Refill;
import com.tankiq.refill.domain.model.queries.GetAllRefillsQuery;
import com.tankiq.refill.domain.model.queries.GetRefillByIdQuery;
import com.tankiq.refill.domain.repositories.RefillRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefillQueryServiceImpl implements RefillQueryService {
    private final RefillRepository refillRepository;

    public RefillQueryServiceImpl(RefillRepository refillRepository) {
        this.refillRepository = refillRepository;
    }

    @Override
    public Result<List<Refill>, ApplicationError> handle(GetAllRefillsQuery query) {
        return Result.success(refillRepository.findAll());
    }

    @Override
    public Result<Refill, ApplicationError> handle(GetRefillByIdQuery query) {
        return refillRepository.findById(query.refillId())
                .<Result<Refill, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.validationError("Refill", "Refill with id '%s' was not found".formatted(query.refillId()))));
    }
}
