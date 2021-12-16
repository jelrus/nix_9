package ua.com.alevel.service.impl;

import ua.com.alevel.dao.DepartmentEmployeeDAO;
import ua.com.alevel.dao.impl.DepartmentEmployeeDAOImpl;
import ua.com.alevel.entity.impl.DepartmentEmployee;
import ua.com.alevel.service.DepartmentEmployeeService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DepartmentEmployeeServiceImpl implements DepartmentEmployeeService {

    private final DepartmentEmployeeDAO departmentEmployeeDAO = new DepartmentEmployeeDAOImpl();


    @Override
    public void create(DepartmentEmployee departmentEmployee) throws IOException, InvocationTargetException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        departmentEmployeeDAO.create(departmentEmployee);
    }

    @Override
    public void update(DepartmentEmployee departmentEmployee) throws IOException, InvocationTargetException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        departmentEmployeeDAO.update(departmentEmployee);
    }

    @Override
    public void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        departmentEmployeeDAO.delete(id);
    }

    @Override
    public DepartmentEmployee findById(String id) throws IOException {
        return departmentEmployeeDAO.findById(id);
    }

    @Override
    public ArrayList<DepartmentEmployee> findAll() throws IOException, InvocationTargetException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        return departmentEmployeeDAO.findAll();
    }
}