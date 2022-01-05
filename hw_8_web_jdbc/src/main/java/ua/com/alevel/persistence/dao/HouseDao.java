package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;

import java.util.List;

public interface HouseDao extends BaseDao<House>{

    DataTableResponse<House> findByOwnerId(DataTableRequest dataTableRequest, Long ownerId);

    long countByOwnerId(Long ownerId);

    List<House> findAll();
}
