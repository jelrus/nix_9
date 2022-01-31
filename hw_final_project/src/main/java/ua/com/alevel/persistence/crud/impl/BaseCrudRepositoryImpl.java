package ua.com.alevel.persistence.crud.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.BaseCrudRepository;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.Optional;

@Service
public class BaseCrudRepositoryImpl<E extends BaseEntity,
        REP extends BaseRepository<E>>
        implements BaseCrudRepository<E, REP> {
    @Override
    public void create(REP repository, E entity) {
        repository.save(entity);
    }

    @Override
    public void update(REP repository, E entity) {
        checkExistence(repository, entity.getId());
        repository.save(entity);
    }

    @Override
    public void delete(REP repository, Long id) {
        checkExistence(repository, id);
        repository.deleteById(id);
    }

    @Override
    public Optional<E> findById(REP repository, Long id) {
        return repository.findById(id);
    }

    @Override
    public DataTableResponse<E> findAll(REP repository, DataTableRequest request) {
        if (MapUtils.isNotEmpty(request.getRequestParamMap())) {
            System.out.println("dataTableRequest = " + request.getRequestParamMap());
        }
        PageRequest pageRequest = PageRequest.of(getPage(request),
                getSize(request),
                getSort(request.getSort(), request.getOrder()));
        Page<E> pageEntity = repository.findAll(pageRequest);
        DataTableResponse<E> response = new DataTableResponse<>();
        setDatatableResponseParams(response,
                request.getSort(),
                request.getOrder(),
                getSize(request),
                getPage(request),
                pageEntity);
        return response;
    }

    private void checkExistence(REP repository, Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entity not found");
        }
    }

    private int getPage(DataTableRequest request) {
        return request.getPage() - 1;
    }

    private int getSize(DataTableRequest request) {
        return request.getSize();
    }

    private Sort getSort(String sortParam, String orderParam) {
        return orderParam.equals("desc") ?
                Sort.by(sortParam).descending() :
                Sort.by(sortParam).ascending();
    }

    private void setDatatableResponseParams(DataTableResponse<E> response, String sortParam, String orderParam,
                                            int size, int page, Page<E> pageEntity) {
        response.setSort(sortParam);
        response.setOrder(orderParam);
        response.setPageSize(size);
        response.setCurrentPage(page);
        response.setItemsSize(pageEntity.getTotalElements());
        response.setTotalPageSize(pageEntity.getTotalPages());
        response.setItems(pageEntity.getContent());
    }
}