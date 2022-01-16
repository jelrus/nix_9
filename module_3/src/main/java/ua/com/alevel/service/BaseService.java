package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity> {

    void create(E entity);

    void update(E entity);

    void delete(Long id);

    Optional<E> findById(Long id);

    List<E> findAll();

    DataTableResponse<E> findAll(DataTableRequest request);
}
