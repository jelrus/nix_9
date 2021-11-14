package ua.com.alevel.controller;

import ua.com.alevel.entity.BaseEntity;

import java.io.IOException;

public interface BaseController<E extends BaseEntity> {

    void run() throws IOException, NoSuchFieldException;

    void create() throws IOException, NoSuchFieldException;

    void update() throws IOException;

    void delete() throws IOException;

    void findById() throws IOException;

    void findAll();
}