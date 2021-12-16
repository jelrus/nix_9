package ua.com.alevel.facade;

import ua.com.alevel.dto.BaseRequestDTO;
import ua.com.alevel.dto.BaseResponseDTO;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public interface BaseFacade<REQ extends BaseRequestDTO, RESP extends BaseResponseDTO> {

    void create(REQ request) throws IOException, InvocationTargetException, NoSuchMethodException,
                                    InstantiationException, IllegalAccessException;

    void update(REQ request, String id) throws IOException, InvocationTargetException, NoSuchMethodException,
                                               InstantiationException, IllegalAccessException;

    void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
                                  InstantiationException, IllegalAccessException;

    RESP findById(String id) throws IOException;

    ArrayList<RESP> findAll() throws IOException, InvocationTargetException, NoSuchMethodException,
                                     InstantiationException, IllegalAccessException;
}