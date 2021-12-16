package ua.com.alevel.service.impl;

import ua.com.alevel.dao.EmployeeDAO;
import ua.com.alevel.dao.impl.DepartmentEmployeeDAOImpl;
import ua.com.alevel.dao.impl.EmployeeDAOImpl;
import ua.com.alevel.entity.impl.Employee;
import ua.com.alevel.service.EmployeeService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private final DepartmentEmployeeDAOImpl departmentEmployeeDAO = new DepartmentEmployeeDAOImpl();


    @Override
    public void create(Employee employee) throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        employeeDAO.create(employee);
    }

    @Override
    public void update(Employee employee) throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        employeeDAO.update(employee);
    }

    @Override
    public void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        Employee employee = employeeDAO.findById(id);
        departmentEmployeeDAO.deleteEmployees(employee.getId());
        employeeDAO.delete(id);
    }

    @Override
    public Employee findById(String id) throws IOException {
        return employeeDAO.findById(id);
    }

    @Override
    public ArrayList<Employee> findAll() throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        return employeeDAO.findAll();
    }
}