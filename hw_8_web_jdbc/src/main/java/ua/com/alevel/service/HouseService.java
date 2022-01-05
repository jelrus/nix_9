package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;

import java.util.List;

public interface HouseService extends BaseService<House> {

    DataTableResponse<House> findByOwnerId(DataTableRequest dataTableRequest, Long ownerId);

    List<House> findAll();
}