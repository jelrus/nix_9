package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.OwnerFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;
import ua.com.alevel.service.HouseService;
import ua.com.alevel.service.OwnerService;
import ua.com.alevel.util.facades.Converter;
import ua.com.alevel.util.web.WebRequestUtil;
import ua.com.alevel.view.dto.request.OwnerDtoRequest;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.HouseDtoResponse;
import ua.com.alevel.view.dto.response.OwnerDtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OwnerFacadeImpl implements OwnerFacade {

    private final OwnerService ownerService;

    public OwnerFacadeImpl(OwnerService ownerService, HouseService houseService) {
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
    public void update(OwnerDtoRequest ownerDtoRequest, long id) {
        Owner owner = new Owner();
        owner.setId(id);
        owner.setFirstName(ownerDtoRequest.getFirstName());
        owner.setLastName(ownerDtoRequest.getLastName());
        owner.setEmail(ownerDtoRequest.getEmail());
        owner.setPhone(ownerDtoRequest.getPhone());
        ownerService.update(owner);
    }

    @Override
    public void delete(long id) {
        ownerService.delete(id);
    }

    @Override
    public OwnerDtoResponse findById(long id) {
        return new OwnerDtoResponse(ownerService.findById(id));
    }

    @Override
    public List<OwnerDtoResponse> findAll() {
        return ownerService.findAll().stream().map(OwnerDtoResponse::new).toList();
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
    public List<HouseDtoResponse> findHouses(Long id) {
        Set<House> houses = ownerService.findHouses(id);
        List<HouseDtoResponse> dto = new ArrayList<>();
        for (House house: houses) {
            HouseDtoResponse houseResp = new HouseDtoResponse(house);
            dto.add(houseResp);
        }
        return dto;
    }

    @Override
    public void addHouse(Long houseId, Long ownerId) {
        ownerService.addHouse(houseId, ownerId);
    }

    @Override
    public void removeHouse(Long houseId, Long ownerId) {
        ownerService.removeHouse(houseId, ownerId);
    }

    private List<OwnerDtoResponse> toDtoList(DataTableResponse<Owner> owners) {
        return owners.getItems().
                stream().
                map(OwnerDtoResponse::new).
                peek(dto -> dto.setHouseCount((Long) owners.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());
    }
}
