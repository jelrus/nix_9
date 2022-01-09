package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;

import java.util.Set;

public interface OwnerService extends BaseService<Owner>{

    DataTableResponse<Owner> findByHouseId(DataTableRequest request, Long houseId);

    Set<House> findHouses(Long id);

    void addHouse(Long houseId, Long ownerId);

    void removeHouse(Long houseId, Long ownerId);
}
