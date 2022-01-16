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
import ua.com.alevel.persistence.entity.Client;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.persistence.repository.AccountRepository;
import ua.com.alevel.persistence.repository.ClientRepository;
import ua.com.alevel.service.ClientService;
import ua.com.alevel.util.input.Checks;

import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {

    @InjectLog
    private final LoggerService loggerService;

    private final BaseCrudRepository<Client, ClientRepository> baseClientRepository;
    private final BaseCrudRepository<Account, AccountRepository> baseAccountRepository;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public ClientServiceImpl(LoggerService loggerService, BaseCrudRepository<Client, ClientRepository> baseClientRepository,
                             BaseCrudRepository<Account, AccountRepository> baseAccountRepository,
                             ClientRepository clientRepository,
                             AccountRepository accountRepository) {
        this.loggerService = loggerService;
        this.baseClientRepository = baseClientRepository;
        this.baseAccountRepository = baseAccountRepository;
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void create(Client client) {
        isValidOnCreate(client);
        loggerService.commit(LoggerLevel.INFO, new Date() + "| client creation started");
        baseClientRepository.create(clientRepository, client);
        loggerService.commit(LoggerLevel.INFO, new Date() + "| client created: " + client);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void update(Client client) {
        isValidOnUpdate(client);
        loggerService.commit(LoggerLevel.INFO, new Date() + "| client with id " + client.getId() + " update started ");
        baseClientRepository.update(clientRepository, client);
        loggerService.commit(LoggerLevel.WARN, new Date() + "| client updated: " + client);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.INFO, new Date() + "| client deletion started");
        baseClientRepository.delete(clientRepository, id);
        loggerService.commit(LoggerLevel.WARN, new Date() + "| client with id " + id + " has been deleted");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findById(Long id) {
        return baseClientRepository.findById(clientRepository, id);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Client> findAll(DataTableRequest request) {
        return baseClientRepository.findAll(clientRepository, request);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Set<Account> findAccounts(Long id) {
        return baseClientRepository.findById(clientRepository, id).get().getAccounts();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<Operation> findOperationsByAccount(Long clientId, Long accountId) {
        Set<Account> accounts = findAccounts(clientId);
        List<Operation> operations = new ArrayList<>();
        for (Account account: accounts) {
            if (account.equals(baseAccountRepository.findById(accountRepository, accountId).get())){
                operations = baseAccountRepository.findById(accountRepository, accountId).get().getOperations();
            }
        }
        return operations;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void addAccount(Long clientId, Long accountId) {
        Client client = baseClientRepository.findById(clientRepository, clientId).get();
        Account account = baseAccountRepository.findById(accountRepository, accountId).get();
        loggerService.commit(LoggerLevel.INFO, new Date() + "| account adding started");
        client.getAccounts().add(account);
        loggerService.commit(LoggerLevel.WARN, new Date() + "| account " + account.getId() + " has been added to client " + client.getId());
        baseClientRepository.update(clientRepository, client);
        loggerService.commit(LoggerLevel.WARN, new Date() +
                "| client with id " + client.getId() + " has been updated after adding account " + account.getId());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void removeAccount(Long clientId, Long accountId) {
        Client client = baseClientRepository.findById(clientRepository, clientId).get();
        Account account = baseAccountRepository.findById(accountRepository, accountId).get();
        loggerService.commit(LoggerLevel.WARN, new Date() + "| client " + client.getId() + " is trying to remove account " + accountId);
        client.getAccounts().remove(account);
        loggerService.commit(LoggerLevel.WARN, new Date() + "| client with id " + client.getId() + " removed account " + accountId);
        baseClientRepository.update(clientRepository, client);
        loggerService.commit(LoggerLevel.WARN, new Date() +
                "| client with id " + client.getId() + " has been updated after removing account " + accountId);
    }

    private void isValidOnCreate(Client client) {
        if (Checks.isBlankNullOrEmpty(client.getFirstName())){
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on creation due to incorrect first name");
            throw new InputException("First name is incorrect");
        }
        if (Checks.isBlankNullOrEmpty(client.getLastName())){
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on creation due to incorrect last name");
            throw new InputException("Last name is incorrect");
        }
        if (Checks.isBlankNullOrEmpty(client.getEmail()) || Checks.isValidEmail(client.getEmail()) ||
            !hasUniqueEmailCreate(client.getEmail())){
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on creation due to incorrect email");
            throw new InputException("Email is incorrect or already in use (Pattern: something@something)");
        }
        if (Checks.isBlankNullOrEmpty(client.getPhone()) || Checks.isValidPhone(client.getPhone()) ||
            !hasUniquePhoneCreate(client.getPhone())) {
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on creation due to incorrect phone");
            throw new InputException("Phone is incorrect or already in use (Pattern: +123456789012)");
        }
    }

    private void isValidOnUpdate(Client client) {
        if (Checks.isBlankNullOrEmpty(client.getFirstName())){
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on updating due to incorrect first name");
            throw new InputException("First name is incorrect");
        }
        if (Checks.isBlankNullOrEmpty(client.getLastName())){
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on updating due to incorrect last name");
            throw new InputException("Last name is incorrect");
        }
        if (Checks.isBlankNullOrEmpty(client.getEmail()) || Checks.isValidEmail(client.getEmail()) || !hasUniqueEmailUpdate(client)){
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on updating due to incorrect email");
            throw new InputException("Email name is incorrect or already in use (Pattern: something@something)");
        }
        if (Checks.isBlankNullOrEmpty(client.getPhone()) || Checks.isValidPhone(client.getPhone()) || !hasUniquePhoneUpdate(client)) {
            loggerService.commit(LoggerLevel.ERROR, new Date() + "| validation failed on updating due to incorrect phone");
            throw new InputException("Phone is incorrect or already in use (Pattern: +123456789012)");
        }
    }

    private boolean hasUniqueEmailCreate(String email) {
        List<Client> clients = clientRepository.findAll();
        if (clients.size() == 0) {
            return true;
        }
        for (Client x: clients){
            return !x.getEmail().equals(email);
        }
        return true;
    }

    private boolean hasUniquePhoneCreate(String phone) {
        List<Client> clients = clientRepository.findAll();
        if (clients.size() == 0) {
            return true;
        }
        for (Client x: clients){
            return !x.getPhone().equals(phone);
        }
        return false;
    }

    private boolean hasUniqueEmailUpdate(Client client) {
        List<Client> clients = clientRepository.findAll();
        Client oldClient = clientRepository.findById(client.getId()).get();
        clients.remove(oldClient);
        if (client.getEmail().equals(oldClient.getEmail())) {
            return true;
        } else {
            for (Client x: clients){
                return !x.getEmail().equals(client.getEmail());
            }
        }
        return true;
    }

    private boolean hasUniquePhoneUpdate(Client client) {
        List<Client> clients = clientRepository.findAll();
        Client oldClient = clientRepository.findById(client.getId()).get();
        clients.remove(oldClient);
        if (client.getPhone().equals(oldClient.getPhone())) {
            return true;
        } else {
            for (Client x: clients){
                return !x.getPhone().equals(client.getPhone());
            }
        }
        return true;
    }
}
