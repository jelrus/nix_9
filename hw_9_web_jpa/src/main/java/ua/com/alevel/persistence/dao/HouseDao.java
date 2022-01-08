package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;

import java.util.Set;

public interface HouseDao extends BaseDao<House>{

    DataTableResponse<House> findByOwnerId(DataTableRequest dataTableRequest, Long ownerId);

    Set<Owner> findOwners(Long id);

    void addOwner(Long houseId, Long ownerId);

    void removeOwner(Long houseId, Long ownerId);
}