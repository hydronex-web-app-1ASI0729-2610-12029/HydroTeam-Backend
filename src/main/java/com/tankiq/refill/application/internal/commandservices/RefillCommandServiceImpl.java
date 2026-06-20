package com.tankiq.refill.application.internal.commandservices;

import com.tankiq.refill.application.commandservices.RefillCommandService;
import com.tankiq.refill.domain.model.aggregates.Refill;
import com.tankiq.refill.domain.model.commands.CreateRefillCommand;
import com.tankiq.refill.domain.model.commands.DeleteRefillCommand;
import com.tankiq.refill.domain.model.commands.UpdateRefillCommand;
import com.tankiq.refill.domain.repositories.RefillRepository;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class RefillCommandServiceImpl implements RefillCommandService {
    private final RefillRepository refillRepository;

    public RefillCommandServiceImpl(RefillRepository refillRepository) {
        this.refillRepository = refillRepository;
    }

    @Override
    public Result<Refill, ApplicationError> handle(CreateRefillCommand command) {
        try {
            var refill = new Refill(command);
            var savedRefill = refillRepository.save(refill);
            return Result.success(savedRefill);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Refill", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Refill creation", e.getMessage()));
        }
    }
    @Override
    public Result<Void, ApplicationError> handle(DeleteRefillCommand command) {

        var refill = refillRepository.findById(command.refillId());

        if (refill.isEmpty()) {
            return Result.failure(
                    ApplicationError.notFound("Refill", command.refillId())
            );
        }

        refillRepository.deleteById(command.refillId());

        return Result.success(null);
    }
    @Override
    public Result<Refill, ApplicationError> handle(UpdateRefillCommand command) {

        var refillOptional = refillRepository.findById(command.refillId());

        if (refillOptional.isEmpty()) {
            return Result.failure(
                    ApplicationError.notFound("Refill", command.refillId())
            );
        }

        var refill = refillOptional.get();

        refill.update(
                command.refillDate(),
                command.liters(),
                command.costSoles(),
                command.supplierName(),
                command.invoiceNumber(),
                command.buildingId(),
                command.registeredByUserId()
        );

        var updatedRefill = refillRepository.save(refill);

        return Result.success(updatedRefill);
    }
}
