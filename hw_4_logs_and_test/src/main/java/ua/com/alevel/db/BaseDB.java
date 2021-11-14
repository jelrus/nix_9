package ua.com.alevel.db;

import customcollections.DynamicArray;
import ua.com.alevel.entity.BaseEntity;

public interface BaseDB<E extends BaseEntity> {

    void create(E entity);

    void update(E entity);

    void delete(String id);

    E findById(String id);

    DynamicArray<E> findAll();

    String generateId();
}