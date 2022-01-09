package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(Long id);

    Optional<ENTITY> findById(Long id);

    List<ENTITY> findAll();

    DataTableResponse<ENTITY> findAll(DataTableRequest request);
}
