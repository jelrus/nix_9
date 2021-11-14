package ua.com.alevel.dao.implementation;

import customcollections.DynamicArray;
import ua.com.alevel.dao.EmployeeDAO;
import ua.com.alevel.db.EmployeeDB;
import ua.com.alevel.db.implementation.EmployeeDBImpl;
import ua.com.alevel.entity.implementation.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final EmployeeDB employeeDB = EmployeeDBImpl.getInstance();

    @Override
    public void create(Employee employee) {
        employeeDB.create(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDB.update(employee);
    }

    @Override
    public void delete(String id) {
        employeeDB.delete(id);
    }

    @Override
    public Employee findById(String id) {
        return employeeDB.findById(id);
    }

    @Override
    public DynamicArray<Employee> findAll() {
        return employeeDB.findAll();
    }
}