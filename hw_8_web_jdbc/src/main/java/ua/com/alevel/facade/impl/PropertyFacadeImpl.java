package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.PropertyFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Property;
import ua.com.alevel.service.HouseService;
import ua.com.alevel.service.OwnerService;
import ua.com.alevel.service.PropertyService;
import ua.com.alevel.util.facades.Converter;
import ua.com.alevel.util.requests.WebRequestUtil;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.PropertyDtoRequest;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PropertyDtoResponse;

import java.util.List;

@Service
public class PropertyFacadeImpl implements PropertyFacade {

    private final HouseService houseService;
    private final OwnerService ownerService;
    private final PropertyService propertyService;

    public PropertyFacadeImpl(HouseService houseService, OwnerService ownerService, PropertyService propertyService) {
        this.houseService = houseService;
        this.ownerService = ownerService;
        this.propertyService = propertyService;
    }

    @Override
    public void create(PropertyDtoRequest propertyDtoRequest) {
        Property property = new Property();
        property.setHouse(houseService.findById(propertyDtoRequest.getHouseId()));
        property.setOwner(ownerService.findById(propertyDtoRequest.getOwnerId()));
        propertyService.create(property);
    }

    @Override
    public void update(PropertyDtoRequest propertyDtoRequest, Long id) {
        Property property = new Property();
        property.setId(id);
        property.setHouse(houseService.findById(propertyDtoRequest.getHouseId()));
        property.setOwner(ownerService.findById(propertyDtoRequest.getOwnerId()));
        propertyService.update(property);
    }

    @Override
    public void delete(Long id) {
        propertyService.delete(id);
    }

    @Override
    public PropertyDtoResponse findById(Long id) {
        return new PropertyDtoResponse(propertyService.findById(id));
    }

    @Override
    public PageData<PropertyDtoResponse> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        if (sortData.getSort().equals("created")) {
            sortData.setSort("property." + sortData.getSort());
        }
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Property> properties = propertyService.findAll(dataTableRequest);
        List<PropertyDtoResponse> responseList = properties.getItems().stream().map(PropertyDtoResponse::new).toList();
        PageData<PropertyDtoResponse> pageData = Converter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(properties.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }
}
