package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.House;
import ua.com.alevel.persistence.entity.Owner;

import java.util.Set;

public interface HouseService extends BaseService<House>{

    DataTableResponse<House> findByOwnerId(DataTableRequest dataTableRequest, Long ownerId);

    Set<Owner> findOwners(Long id);

    void addOwner(Long houseId, Long ownerId);

    void removeOwner(Long houseId, Long ownerId);
}
