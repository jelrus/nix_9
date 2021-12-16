package ua.com.alevel.db;

import ua.com.alevel.entity.BaseEntity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public interface BaseDB<E extends BaseEntity> {

    void create(E entity) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                                 IllegalAccessException;

    void update(E entity) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                                 IllegalAccessException;

    void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                                  IllegalAccessException;

    E findById(String id) throws IOException;

    ArrayList<E> findAll() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                                  IllegalAccessException;
}