package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.EmployeeDAO;
import ua.com.alevel.db.impl.EmployeeDBImpl;
import ua.com.alevel.entity.impl.Employee;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final EmployeeDBImpl employeeDB = EmployeeDBImpl.getInstance();

    @Override
    public void create(Employee employee) throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        employeeDB.create(employee);
    }

    @Override
    public void update(Employee employee) throws IOException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        employeeDB.update(employee);
    }

    @Override
    public void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        employeeDB.delete(id);
    }

    @Override
    public Employee findById(String id) throws IOException {
        return employeeDB.findById(id);
    }

    @Override
    public ArrayList<Employee> findAll() throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        return employeeDB.findAll();
    }
}