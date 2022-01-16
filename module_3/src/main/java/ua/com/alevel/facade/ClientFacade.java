package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.view.dto.request.ClientDtoRequest;
import ua.com.alevel.view.dto.response.AccountDtoResponse;
import ua.com.alevel.view.dto.response.ClientDtoResponse;
import ua.com.alevel.view.dto.response.OperationDtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;

public interface ClientFacade extends BaseFacade<ClientDtoRequest, ClientDtoResponse>{

    List<AccountDtoResponse> findAccounts(Long id);

    List<OperationDtoResponse> findOperationsByAccount(Long clientId, Long accountId);

    void addAccount(Long clientId, Long accountId);

    void removeAccount(Long clientId, Long accountId);
}
