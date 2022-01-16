package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.config.annotations.InjectLog;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Client;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.util.facades.Converter;
import ua.com.alevel.util.web.WebRequestUtil;
import ua.com.alevel.view.dto.request.AccountDtoRequest;
import ua.com.alevel.view.dto.request.OperationDtoRequest;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.AccountDtoResponse;
import ua.com.alevel.view.dto.response.ClientDtoResponse;
import ua.com.alevel.view.dto.response.OperationDtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;

    public AccountFacadeImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void create(AccountDtoRequest request) {
        Account account = new Account();
        account.setAccountNumber(request.getAccountNumber());
        account.setBalance(request.getBalance());
        accountService.create(account);
    }

    @Override
    public void update(AccountDtoRequest request, long id) {
        Account account = accountService.findById(id).get();
        account.setId(id);
        account.setAccountNumber(request.getAccountNumber());
        account.setBalance(request.getBalance());
        accountService.update(account);
    }

    @Override
    public void delete(long id) {
        accountService.delete(id);
    }

    @Override
    public AccountDtoResponse findById(long id) {
        return new AccountDtoResponse(accountService.findById(id).get());
    }

    @Override
    public List<AccountDtoResponse> findAll() {
        return accountService.findAll().stream().map(AccountDtoResponse::new).toList();
    }

    @Override
    public PageData<AccountDtoResponse> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Account> accounts = accountService.findAll(dataTableRequest);
        List<AccountDtoResponse> responseList = toDtoList(accounts);
        PageData<AccountDtoResponse> pageData = Converter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(accounts.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<OperationDtoResponse> findOperations(Long id) {
        List<Operation> operations = accountService.findOperations(id);
        List<OperationDtoResponse> dto = new ArrayList<>();
        for (Operation operation: operations) {
            OperationDtoResponse accountResp = new OperationDtoResponse(operation);
            dto.add(accountResp);
        }
        return dto;
    }

    @Override
    public void addOperation(Long accountId, Long operationId) {
        accountService.addOperation(accountId, operationId);
    }

    @Override
    public void removeOperation(Long accountId, Long operationId) {
        accountService.removeOperation(accountId, operationId);
    }

    @Override
    public void makeTransaction(Long senderId, Long outcomeId, Long recipientId, Long incomeId) {
        accountService.makeTransaction(senderId, outcomeId, recipientId, incomeId);
    }

    private List<AccountDtoResponse> toDtoList(DataTableResponse<Account> accounts) {
        return accounts.getItems().
                stream().
                map(AccountDtoResponse::new).
                peek(dto -> dto.setOperationCount((Long) accounts.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());
    }
}
