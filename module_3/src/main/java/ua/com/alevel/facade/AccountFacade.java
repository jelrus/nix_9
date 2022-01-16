package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.view.dto.request.AccountDtoRequest;
import ua.com.alevel.view.dto.request.OperationDtoRequest;
import ua.com.alevel.view.dto.response.AccountDtoResponse;
import ua.com.alevel.view.dto.response.OperationDtoResponse;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface AccountFacade extends BaseFacade<AccountDtoRequest, AccountDtoResponse>{

    List<OperationDtoResponse> findOperations(Long id);

    void addOperation(Long accountId, Long operationId);

    void removeOperation(Long accountId, Long operationId);

    void makeTransaction(Long senderId, Long outcomeId, Long recipientId, Long incomeId);
}
