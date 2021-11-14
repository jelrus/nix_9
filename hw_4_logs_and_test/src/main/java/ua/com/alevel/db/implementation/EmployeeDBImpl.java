package ua.com.alevel.db.implementation;

import customcollections.DynamicArray;
import ua.com.alevel.db.EmployeeDB;
import ua.com.alevel.entity.implementation.Employee;

import java.util.Objects;
import java.util.UUID;

public class EmployeeDBImpl implements EmployeeDB {

    private static EmployeeDBImpl instance;
    private final DynamicArray<Employee> employees;

    private EmployeeDBImpl() {
        employees = new DynamicArray<>();
    }

    public static EmployeeDBImpl getInstance() {
        if (instance == null) {
            instance = new EmployeeDBImpl();
        }
        return instance;
    }

    @Override
    public void create(Employee employee) {
        employee.setId(generateId());
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setAge(employee.getAge());
        employee.setEmail(employee.getEmail());
        employee.setSalary(employee.getSalary());
        employee.setDepartments(employee.getDepartments());
        employees.add(employee);
    }

    @Override
    public void update(Employee employee) {
        Employee current = findById(employee.getId());
        current.setFirstName(current.getFirstName());
        current.setLastName(current.getLastName());
        current.setAge(current.getAge());
        current.setEmail(current.getEmail());
        current.setSalary(current.getSalary());
        current.setDepartments(current.getDepartments());
    }

    @Override
    public void delete(String id) {
        for (int i = 0; i < employees.size(); i++) {
            if (Objects.equals(employees.get(i).getId(), id)) {
                employees.remove(i);
            }
        }
    }

    @Override
    public Employee findById(String id) {
        Employee result = null;
        for (int i = 0; i < employees.size(); i++) {
            if (Objects.equals(employees.get(i).getId(), id)) {
                result = employees.get(i);
            }
        }
        return result;
    }

    @Override
    public DynamicArray<Employee> findAll() {
        return employees;
    }

    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}