package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.HouseFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.service.HouseService;
import ua.com.alevel.util.facades.Converter;
import ua.com.alevel.util.requests.WebRequestUtil;
import ua.com.alevel.view.dto.request.HouseDtoRequest;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.HouseDtoResponse;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
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
    public void update(HouseDtoRequest houseDtoRequest, Long id) {
        House house = new House();
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
    public void delete(Long id) {
        houseService.delete(id);
    }

    @Override
    public HouseDtoResponse findById(Long id) {
        return new HouseDtoResponse(houseService.findById(id));
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
    public List<HouseDtoResponse> findAll() {
        return houseService.findAll().stream().map(HouseDtoResponse::new).toList();
    }

    private List<HouseDtoResponse> toDtoList(DataTableResponse<House> houses) {
        return houses.getItems().
                stream().
                map(HouseDtoResponse::new).
                peek(dto -> dto.setOwnerCount((Long) houses.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());
    }
}
