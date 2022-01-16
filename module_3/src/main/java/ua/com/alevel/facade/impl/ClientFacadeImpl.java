package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.ClientFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Client;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.ClientService;
import ua.com.alevel.util.facades.Converter;
import ua.com.alevel.util.web.WebRequestUtil;
import ua.com.alevel.view.dto.request.ClientDtoRequest;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.AccountDtoResponse;
import ua.com.alevel.view.dto.response.ClientDtoResponse;
import ua.com.alevel.view.dto.response.OperationDtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientFacadeImpl implements ClientFacade {

    private final ClientService clientService;
    private final AccountService accountService;

    public ClientFacadeImpl(ClientService clientService, AccountService accountService) {
        this.clientService = clientService;
        this.accountService = accountService;
    }

    @Override
    public void create(ClientDtoRequest request) {
        Client client = new Client();
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());
        clientService.create(client);
    }

    @Override
    public void update(ClientDtoRequest request, long id) {
        Client client = clientService.findById(id).get();
        client.setId(id);
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());
        clientService.update(client);
    }

    @Override
    public void delete(long id) {
        clientService.delete(id);
    }

    @Override
    public ClientDtoResponse findById(long id) {
        return new ClientDtoResponse(clientService.findById(id).get());
    }

    @Override
    public List<ClientDtoResponse> findAll() {
        return clientService.findAll().stream().map(ClientDtoResponse::new).toList();
    }

    @Override
    public PageData<ClientDtoResponse> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Client> clients = clientService.findAll(dataTableRequest);
        List<ClientDtoResponse> responseList = toDtoList(clients);
        PageData<ClientDtoResponse> pageData = Converter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(clients.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<AccountDtoResponse> findAccounts(Long id) {
        Set<Account> accounts = clientService.findAccounts(id);
        List<AccountDtoResponse> dto = new ArrayList<>();
        for (Account account: accounts) {
            AccountDtoResponse accountResp = new AccountDtoResponse(account);
            dto.add(accountResp);
        }
        return dto;
    }

    @Override
    public List<OperationDtoResponse> findOperationsByAccount(Long clientId, Long accountId) {
        List<Operation> operations = clientService.findOperationsByAccount(clientId, accountId);
        List<OperationDtoResponse> dto = new ArrayList<>();
        for (Operation operation: operations) {
            OperationDtoResponse operationResp = new OperationDtoResponse(operation);
            dto.add(operationResp);
        }
        return dto;
    }

    @Override
    public void addAccount(Long clientId, Long accountId) {
        clientService.addAccount(clientId, accountId);
    }

    @Override
    public void removeAccount(Long clientId, Long accountId) {
        clientService.removeAccount(clientId, accountId);
    }

    private List<ClientDtoResponse> toDtoList(DataTableResponse<Client> clients) {
        return clients.getItems().
                stream().
                map(ClientDtoResponse::new).
                peek(dto -> dto.setAccountCount((Long) clients.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());
    }
}
