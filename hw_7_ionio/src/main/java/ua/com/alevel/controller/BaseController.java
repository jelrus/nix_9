package ua.com.alevel.controller;

import ua.com.alevel.entity.BaseEntity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface BaseController<E extends BaseEntity> {

    void run() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException;

    void create() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException;

    void update() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException;

    void delete() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException;

    void findById() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    void findAll() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException;
}