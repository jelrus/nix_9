package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.config.annotations.InjectLog;
import ua.com.alevel.exceptions.InputException;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.BaseCrudRepository;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.persistence.repository.OperationRepository;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.util.input.Checks;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl implements OperationService {

    @InjectLog
    private final LoggerService loggerService;

    private final BaseCrudRepository<Operation, OperationRepository> baseOperationRepository;
    private final OperationRepository operationRepository;

    public OperationServiceImpl(LoggerService loggerService, BaseCrudRepository<Operation, OperationRepository> baseOperationRepository,
                                OperationRepository operationRepository) {
        this.loggerService = loggerService;
        this.baseOperationRepository = baseOperationRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void create(Operation operation) {
        isValid(operation);
        loggerService.commit(LoggerLevel.INFO, new Date() + "| operation creation started");
        baseOperationRepository.create(operationRepository, operation);
        loggerService.commit(LoggerLevel.INFO, new Date() + "| operation created: " + operation);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void update(Operation operation) {
        isValid(operation);
        loggerService.commit(LoggerLevel.INFO, new Date() + "| operation with id " + operation.getId() + " update started ");
        baseOperationRepository.update(operationRepository, operation);
        loggerService.commit(LoggerLevel.WARN, new Date() + "| operation updated: " + operation);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.INFO, new Date() + "| operation deletion started");
        baseOperationRepository.delete(operationRepository, id);
        loggerService.commit(LoggerLevel.WARN, new Date() + "| operation with id " + id + " has been deleted");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Operation> findById(Long id) {
        return baseOperationRepository.findById(operationRepository, id);
    }

    @Override
    public List<Operation> findAll() {
        return operationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Operation> findAll(DataTableRequest request) {
        return baseOperationRepository.findAll(operationRepository, request);
    }

    private void isValid(Operation operation) {
        if (Checks.isBlankNullOrEmpty(operation.getName())) {
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on creation or updating due to incorrect name");
            throw new InputException("Incorrect name");
        }
        if (Checks.isBlankNullOrEmpty(operation.getDescription())) {
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on update or updating due to incorrect description");
            throw new InputException("Incorrect description");
        }
        if (Checks.isBlankNullOrEmpty(operation.getSum().toString()) || !Checks.isPositiveNumberNotZero(operation.getSum().toString())) {
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on creation due to incorrect sum");
            throw new InputException("Incorrect sum");
        }
    }
}
