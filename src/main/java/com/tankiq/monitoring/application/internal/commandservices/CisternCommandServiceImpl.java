package com.tankiq.monitoring.application.internal.commandservices;

import com.tankiq.monitoring.application.commandservices.CisternCommandService;
import com.tankiq.monitoring.domain.model.aggregates.Cistern;
import com.tankiq.monitoring.domain.model.commands.CreateCisternCommand;
import com.tankiq.monitoring.domain.repositories.CisternRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class CisternCommandServiceImpl implements CisternCommandService {
    private final CisternRepository cisternRepository;

    public CisternCommandServiceImpl(CisternRepository cisternRepository) {
        this.cisternRepository = cisternRepository;
    }

    @Override
    public Result<Cistern, ApplicationError> handle(CreateCisternCommand command) {
        try {
            var cistern = new Cistern(command);
            var savedCistern = cisternRepository.save(cistern);
            return Result.success(savedCistern);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Cistern", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Cistern creation", e.getMessage()));
        }
    }
}
