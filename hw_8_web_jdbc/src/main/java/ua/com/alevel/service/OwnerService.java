package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Owner;

import java.util.List;

public interface OwnerService extends BaseService<Owner> {

    DataTableResponse<Owner> findByHouseId(DataTableRequest dataTableRequest, Long houseId);

    List<Owner> findAll();
}