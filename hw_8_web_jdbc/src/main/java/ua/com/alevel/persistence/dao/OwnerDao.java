package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Owner;

import java.util.List;

public interface OwnerDao extends BaseDao<Owner>{

    DataTableResponse<Owner> findByHouseId(DataTableRequest dataTableRequest, Long houseId);

    long countByHouseId(Long houseId);

    List<Owner> findAll();
}
