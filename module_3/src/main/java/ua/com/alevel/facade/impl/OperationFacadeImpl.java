package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.OperationFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.util.facades.Converter;
import ua.com.alevel.util.web.WebRequestUtil;
import ua.com.alevel.view.dto.request.OperationDtoRequest;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.OperationDtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationFacadeImpl implements OperationFacade {

    private final OperationService operationService;

    public OperationFacadeImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public void create(OperationDtoRequest request) {
        Operation operation = new Operation();
        operation.setName(request.getName());
        operation.setOperationType(request.getOperationType());
        operation.setDescription(request.getDescription());
        operation.setSum(request.getSum());
        operationService.create(operation);
    }

    @Override
    public void update(OperationDtoRequest request, long id) {
        Operation operation = operationService.findById(id).get();
        operation.setId(id);
        operation.setName(request.getName());
        operation.setOperationType(request.getOperationType());
        operation.setDescription(request.getDescription());
        operation.setSum(request.getSum());
        operationService.update(operation);
    }

    @Override
    public void delete(long id) {
        operationService.delete(id);
    }

    @Override
    public OperationDtoResponse findById(long id) {
        return new OperationDtoResponse(operationService.findById(id).get());
    }

    @Override
    public List<OperationDtoResponse> findAll() {
        return operationService.findAll().stream().map(OperationDtoResponse::new).toList();
    }

    @Override
    public PageData<OperationDtoResponse> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Operation> operations = operationService.findAll(dataTableRequest);
        List<OperationDtoResponse> responseList = toDtoList(operations);
        PageData<OperationDtoResponse> pageData = Converter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(operations.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<OperationDtoResponse> toDtoList(DataTableResponse<Operation> operations) {
        return operations.getItems()
                         .stream()
                         .map(OperationDtoResponse::new)
                         .collect(Collectors.toList());
    }
}
