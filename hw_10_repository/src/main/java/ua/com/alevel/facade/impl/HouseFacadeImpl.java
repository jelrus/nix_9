package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.HouseFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;
import ua.com.alevel.service.HouseService;
import ua.com.alevel.util.facades.Converter;
import ua.com.alevel.util.web.WebRequestUtil;
import ua.com.alevel.view.dto.request.HouseDtoRequest;
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
public class HouseFacadeImpl implements HouseFacade {

    private final HouseService houseService;

    public HouseFacadeImpl(HouseService houseService) {
        this.houseService = houseService;
    }

    @Override
    public void create(HouseDtoRequest houseDtoRequest) {
        House house = new House();
        house.setImage(houseDtoRequest.getImage());
        house.setCountry(houseDtoRequest.getCountry());
        house.setCity(houseDtoRequest.getCity());
        house.setStreet(houseDtoRequest.getStreet());
        house.setBuildingNumber(houseDtoRequest.getBuildingNumber());
        house.setStatus(houseDtoRequest.getStatus());
        house.setCost(houseDtoRequest.getCost());
        houseService.create(house);
    }

    @Override
    public void update(HouseDtoRequest houseDtoRequest, long id) {
        House house = houseService.findById(id).get();
        house.setId(id);
        house.setImage(houseDtoRequest.getImage());
        house.setCountry(houseDtoRequest.getCountry());
        house.setCity(houseDtoRequest.getCity());
        house.setStreet(houseDtoRequest.getStreet());
        house.setBuildingNumber(houseDtoRequest.getBuildingNumber());
        house.setStatus(houseDtoRequest.getStatus());
        house.setCost(houseDtoRequest.getCost());
        houseService.update(house);
    }

    @Override
    public void delete(long id) {
        houseService.delete(id);
    }

    @Override
    public HouseDtoResponse findById(long id) {
        return new HouseDtoResponse(houseService.findById(id).get());
    }

    @Override
    public List<HouseDtoResponse> findAll() {
        return houseService.findAll().stream().map(HouseDtoResponse::new).toList();
    }

    @Override
    public PageData<HouseDtoResponse> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<House> houses = houseService.findAll(dataTableRequest);
        List<HouseDtoResponse> responseList = toDtoList(houses);
        PageData<HouseDtoResponse> pageData = Converter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(houses.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PageData<HouseDtoResponse> findByOwnerId(WebRequest request, Long ownerId) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<House> houses = houseService.findByOwnerId(dataTableRequest, ownerId);
        List<HouseDtoResponse> responseList = toDtoList(houses);
        PageData<HouseDtoResponse> pageData = Converter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(houses.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<OwnerDtoResponse> findOwners(Long id) {
        Set<Owner> owners = houseService.findOwners(id);
        List<OwnerDtoResponse> dto = new ArrayList<>();
        for (Owner owner: owners) {
            OwnerDtoResponse ownerResp = new OwnerDtoResponse(owner);
            dto.add(ownerResp);
        }
        return dto;
    }

    @Override
    public void addOwner(Long houseId, Long ownerId) {
        houseService.addOwner(houseId, ownerId);
    }

    @Override
    public void removeOwner(Long houseId, Long ownerId) {
        houseService.removeOwner(houseId, ownerId);
    }

    private List<HouseDtoResponse> toDtoList(DataTableResponse<House> houses) {
        return houses.getItems().
                stream().
                map(HouseDtoResponse::new).
                peek(dto -> dto.setOwnerCount((Long) houses.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());
    }
}
