package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;

import java.util.Set;

public interface OwnerDao extends BaseDao<Owner>{

    DataTableResponse<Owner> findByHouseId(DataTableRequest dataTableRequest, Long houseId);

    Set<House> findHouses(Long id);

    void addHouse(Long houseId, Long ownerId);

    void removeHouse(Long houseId, Long ownerId);
}
