package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Operation;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Set;

public interface AccountService extends BaseService<Account>{

    List<Operation> findOperations(Long id);

    void addOperation(Long accountId, Long operationId);

    void removeOperation(Long accountId, Long operationId);

    void makeTransaction(Long senderId, Long outcomeId, Long recipientId, Long incomeId);
}