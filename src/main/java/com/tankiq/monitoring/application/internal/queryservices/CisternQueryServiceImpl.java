package com.tankiq.monitoring.application.internal.queryservices;

import com.tankiq.monitoring.application.queryservices.CisternQueryService;
import com.tankiq.monitoring.domain.model.aggregates.Cistern;
import com.tankiq.monitoring.domain.model.queries.GetAllCisternsQuery;
import com.tankiq.monitoring.domain.model.queries.GetCisternByIdQuery;
import com.tankiq.monitoring.domain.repositories.CisternRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CisternQueryServiceImpl implements CisternQueryService {
    private final CisternRepository cisternRepository;

    public CisternQueryServiceImpl(CisternRepository cisternRepository) {
        this.cisternRepository = cisternRepository;
    }

    @Override
    public Result<List<Cistern>, ApplicationError> handle(GetAllCisternsQuery query) {
        return Result.success(cisternRepository.findAll());
    }

    @Override
    public Result<Cistern, ApplicationError> handle(GetCisternByIdQuery query) {
        return cisternRepository.findById(query.cisternId())
                .<Result<Cistern, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.validationError("Cistern", "Cistern with id '%s' was not found".formatted(query.cisternId()))));
    }
}
