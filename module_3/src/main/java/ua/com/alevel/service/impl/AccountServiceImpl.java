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
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.persistence.entity.type.OperationType;
import ua.com.alevel.persistence.repository.AccountRepository;
import ua.com.alevel.persistence.repository.OperationRepository;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.util.input.Checks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @InjectLog
    private final LoggerService loggerService;

    private final BaseCrudRepository<Account, AccountRepository> baseAccountRepository;
    private final BaseCrudRepository<Operation, OperationRepository> baseOperationRepository;
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    public AccountServiceImpl(LoggerService loggerService, BaseCrudRepository<Account, AccountRepository> baseAccountRepository,
                              BaseCrudRepository<Operation, OperationRepository> baseOperationRepository,
                              AccountRepository accountRepository,
                              OperationRepository operationRepository) {
        this.loggerService = loggerService;
        this.baseAccountRepository = baseAccountRepository;
        this.baseOperationRepository = baseOperationRepository;
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void create(Account account) {
        isValidOnCreate(account);
        loggerService.commit(LoggerLevel.INFO, new Date() + "| account creation started");
        baseAccountRepository.create(accountRepository, account);
        loggerService.commit(LoggerLevel.INFO, new Date() + "| account created: " + account);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void update(Account account) {
        isValidOnUpdate(account);
        loggerService.commit(LoggerLevel.INFO, new Date() + "| account with id " + account.getId() + " update started ");
        baseAccountRepository.create(accountRepository, account);
        loggerService.commit(LoggerLevel.WARN, new Date() + "| account updated: " + account);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.INFO, new Date() + "| account deletion started");
        baseAccountRepository.delete(accountRepository, id);
        loggerService.commit(LoggerLevel.WARN, new Date() + "| account with id " + id + " has been deleted");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findById(Long id) {
        return baseAccountRepository.findById(accountRepository, id);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Account> findAll(DataTableRequest request) {
        return baseAccountRepository.findAll(accountRepository, request);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<Operation> findOperations(Long id) {
        return baseAccountRepository.findById(accountRepository, id).get().getOperations();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void addOperation(Long accountId, Long operationId) {
        Account account = baseAccountRepository.findById(accountRepository, accountId).get();
        Operation operation = baseOperationRepository.findById(operationRepository, operationId).get();
        if (balanceIsNotNegative(account, operation)) {
            if (operation.getOperationType().equals(OperationType.OUTCOME)) {
                account.setBalance(account.getBalance().subtract(operation.getSum()));
            } else {
                account.setBalance(account.getBalance().add(operation.getSum()));
            }
            loggerService.commit(LoggerLevel.INFO, new Date() +
                                   "| operation adding started");
            account.getOperations().add(operation);
            loggerService.commit(LoggerLevel.WARN, new Date() +
                                   "| operation " + operation.getId() + " has been added to account " + account.getId());
            baseAccountRepository.update(accountRepository, account);
            loggerService.commit(LoggerLevel.WARN, new Date() +
                  "| account with id " + account.getId() + " has been updated after adding operation " + operation.getId());
        } else  {
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| adding operation " + operation.getId() + " failed");
            throw new InputException("Insufficient funds");
        }
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void removeOperation(Long accountId, Long operationId) {
        Account account = baseAccountRepository.findById(accountRepository, accountId).get();
        Operation operation = baseOperationRepository.findById(operationRepository, operationId).get();
        loggerService.commit(LoggerLevel.WARN, new Date() + "| account " + account.getId() + " is trying to remove operation " + operationId);
        account.getOperations().remove(operation);
        loggerService.commit(LoggerLevel.WARN, new Date() + "| account with id " + account.getId() + " removed operation " + operationId);
        baseAccountRepository.update(accountRepository, account);
        loggerService.commit(LoggerLevel.WARN, new Date() +
                "| account with id " + account.getId() + " has been updated after removing operation " + operationId);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void makeTransaction(Long senderId, Long outcomeId, Long recipientId, Long incomeId) {
        loggerService.commit(LoggerLevel.INFO, new Date() + "| transaction started");
        Account sender = baseAccountRepository.findById(accountRepository, senderId).get();
        Account recipient = baseAccountRepository.findById(accountRepository, recipientId).get();
        if (balanceIsNotNegative(sender, operationRepository.findById(outcomeId).get())) {
            sender.getOperations().add(operationRepository.findById(outcomeId).get());
            loggerService.commit(LoggerLevel.WARN, new Date() +
                    "| to account with id " + senderId + " operation with id " + outcomeId + " has been added");
            recipient.getOperations().add(operationRepository.findById(incomeId).get());
            loggerService.commit(LoggerLevel.WARN, new Date() +
                    "| to account with id " + recipientId + " operation with id " + incomeId + " has been added");
            sender.setBalance(sender.getBalance().subtract(operationRepository.findById(outcomeId).get().getSum()));
            recipient.setBalance(recipient.getBalance().add(operationRepository.findById(incomeId).get().getSum()));
        } else {
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| transaction failed due to insufficient funds");
            throw new InputException("Insufficient funds");
        }
    }

    private void isValidOnCreate(Account account) {
        if (Checks.isBlankNullOrEmpty(account.getAccountNumber()) || Checks.isValidAccountNumber(account.getAccountNumber())
                || !hasUniqueAccountNumberCreate(account.getAccountNumber())){
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on creation due to incorrect account number");
            throw new InputException("Account number is incorrect (Pattern: 1234-5678-9012-3456)");
        }
        if (Checks.isBlankNullOrEmpty(account.getBalance().toString()) || !Checks.isPositiveNumber(account.getBalance().toString())){
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on creation due to incorrect balance");
            throw new InputException("Incorrect balance");
        }
    }

    private void isValidOnUpdate(Account account) {
        if (Checks.isBlankNullOrEmpty(account.getAccountNumber()) || Checks.isValidAccountNumber(account.getAccountNumber())
            || !hasUniqueAccountNumberUpdate(account)){
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on update due to incorrect account number");
            throw new InputException("Account number is incorrect or already in use (Pattern: 1234-5678-9012-3456)");
        }
        if (Checks.isBlankNullOrEmpty(account.getBalance().toString()) || !Checks.isPositiveNumber(account.getBalance().toString())){
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on update due to incorrect balance");
            throw new InputException("Incorrect balance");
        }
    }

    private boolean hasUniqueAccountNumberCreate(String accountNumber) {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.size() == 0) {
            return true;
        }
        for (Account x: accounts){
            return !x.getAccountNumber().equals(accountNumber);
        }
        return false;
    }

    private boolean hasUniqueAccountNumberUpdate(Account account) {
        List<Account> accounts = accountRepository.findAll();
        Account oldAccount = accountRepository.findById(account.getId()).get();
        accounts.remove(oldAccount);
        if (account.getAccountNumber().equals(oldAccount.getAccountNumber())) {
            return true;
        } else {
            for (Account x: accounts){
                return !x.getAccountNumber().equals(account.getAccountNumber());
            }
        }
        return true;
    }

    private boolean balanceIsNotNegative(Account account, Operation operation) {
        BigDecimal rest = account.getBalance().subtract(operation.getSum());
        return rest.doubleValue() >= 0;
    }
}
