package ua.com.alevel.service;

import customcollections.DynamicArray;
import ua.com.alevel.entity.BaseEntity;

public interface BaseService<E extends BaseEntity> {

    void create(E entity);

    void update(E entity);

    void delete(String id);

    E findById(String id);

    DynamicArray<E> findAll();
}