package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Client;
import ua.com.alevel.persistence.entity.Operation;

import java.util.List;
import java.util.Set;

public interface ClientService extends BaseService<Client>{

    Set<Account> findAccounts(Long id);

    List<Operation> findOperationsByAccount(Long clientId, Long accountId);

    void addAccount(Long clientId, Long accountId);

    void removeAccount(Long clientId, Long accountId);
}
