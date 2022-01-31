package ua.com.alevel.persistence.crud;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.Optional;

public interface BaseCrudRepository<E extends BaseEntity, REP extends BaseRepository<E>> {

    void create(REP repository, E entity);

    void update(REP repository, E entity);

    void delete(REP repository, Long id);

    Optional<E> findById(REP repository, Long id);

    DataTableResponse<E> findAll(REP repository, DataTableRequest dataTableRequest);

}
