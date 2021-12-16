package ua.com.alevel.service.impl;

import ua.com.alevel.dao.DepartmentDAO;
import ua.com.alevel.dao.DepartmentEmployeeDAO;
import ua.com.alevel.dao.impl.DepartmentDAOImpl;
import ua.com.alevel.dao.impl.DepartmentEmployeeDAOImpl;
import ua.com.alevel.entity.impl.Department;
import ua.com.alevel.service.DepartmentService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDAO departmentDAO = new DepartmentDAOImpl();
    private final DepartmentEmployeeDAO departmentEmployeeDAO = new DepartmentEmployeeDAOImpl();

    @Override
    public void create(Department department) throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        departmentDAO.create(department);
    }

    @Override
    public void update(Department department) throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        departmentDAO.update(department);
    }

    @Override
    public void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        Department department = departmentDAO.findById(id);
        departmentEmployeeDAO.deleteDepartments(department.getId());
        departmentDAO.delete(id);
    }

    @Override
    public Department findById(String id) throws IOException {
        return departmentDAO.findById(id);
    }

    @Override
    public ArrayList<Department> findAll() throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        return departmentDAO.findAll();
    }
}