package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.OwnerFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Owner;
import ua.com.alevel.service.OwnerService;
import ua.com.alevel.util.facades.Converter;
import ua.com.alevel.util.requests.WebRequestUtil;
import ua.com.alevel.view.dto.request.OwnerDtoRequest;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.OwnerDtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerFacadeImpl implements OwnerFacade {

    private final OwnerService ownerService;

    public OwnerFacadeImpl(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public void create(OwnerDtoRequest ownerDtoRequest) {
        Owner owner = new Owner();
        owner.setFirstName(ownerDtoRequest.getFirstName());
        owner.setLastName(ownerDtoRequest.getLastName());
        owner.setEmail(ownerDtoRequest.getEmail());
        owner.setPhone(ownerDtoRequest.getPhone());
        ownerService.create(owner);
    }

    @Override
    public void update(OwnerDtoRequest ownerDtoRequest, Long id) {
        Owner owner = new Owner();
        owner.setId(id);
        owner.setFirstName(ownerDtoRequest.getFirstName());
        owner.setLastName(ownerDtoRequest.getLastName());
        owner.setEmail(ownerDtoRequest.getEmail());
        owner.setPhone(ownerDtoRequest.getPhone());
        ownerService.update(owner);
    }

    @Override
    public void delete(Long id) {
        ownerService.delete(id);
    }

    @Override
    public OwnerDtoResponse findById(Long id) {
        return new OwnerDtoResponse(ownerService.findById(id));
    }

    @Override
    public PageData<OwnerDtoResponse> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Owner> owners = ownerService.findAll(dataTableRequest);
        List<OwnerDtoResponse> items = owners.getItems().stream().map(OwnerDtoResponse::new).collect(Collectors.toList());
        PageData<OwnerDtoResponse> pageData = Converter.dtoRespToPageAndSortData(items, pageAndSizeData, sortData);
        pageData.setItemsSize(owners.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PageData<OwnerDtoResponse> findByHouseId(WebRequest request, Long houseId) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Owner> owners = ownerService.findByHouseId(dataTableRequest, houseId);
        List<OwnerDtoResponse> items = owners.getItems().stream().map(OwnerDtoResponse::new).toList();
        PageData<OwnerDtoResponse> pageData = Converter.dtoRespToPageAndSortData(items, pageAndSizeData, sortData);
        pageData.setItemsSize(owners.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<OwnerDtoResponse> findAll() {
        return ownerService.findAll().stream().map(OwnerDtoResponse::new).toList();
    }
}
