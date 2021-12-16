package ua.com.alevel.db.impl;

import csvio.CSVReaderWriter;
import ua.com.alevel.db.EmployeeDB;
import ua.com.alevel.entity.impl.Department;
import ua.com.alevel.entity.impl.Employee;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

public class EmployeeDBImpl implements EmployeeDB {

    public static final String PATH_TO_EMP_CSV = "employees.csv";
    private static EmployeeDBImpl instance;
    private final ArrayList<Employee> employees;

    private EmployeeDBImpl() {
        employees = new ArrayList<>();
    }

    public static EmployeeDBImpl getInstance() {
        if (instance == null) {
            instance = new EmployeeDBImpl();
        }
        return instance;
    }

    @Override
    public void create(Employee employee) throws IOException, InvocationTargetException, NoSuchMethodException,
                                                 InstantiationException, IllegalAccessException {
        employee.setId(generateId());
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setAge(employee.getAge());
        employee.setEmail(employee.getEmail());
        employee.setSalary(employee.getSalary());
        employees.add(employee);
        CSVReaderWriter.createRecord(PATH_TO_EMP_CSV, employee, employees);
    }

    @Override
    public void update(Employee employee) throws IOException, InvocationTargetException, NoSuchMethodException,
                                                 InstantiationException, IllegalAccessException {
        Employee current = findById(employee.getId());
        current.setFirstName(employee.getFirstName());
        current.setLastName(employee.getLastName());
        current.setAge(employee.getAge());
        current.setEmail(employee.getEmail());
        current.setSalary(employee.getSalary());
        CSVReaderWriter.createRecord(PATH_TO_EMP_CSV, employee, employees);
    }

    @Override
    public void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
                                         InstantiationException, IllegalAccessException {
        Employee employee = findById(id);
        employees.remove(employee);
        CSVReaderWriter.createRecord(PATH_TO_EMP_CSV, employee, employees);
    }

    @Override
    public Employee findById(String id) throws IOException {
        return employees.stream().filter(x -> x.getId().equals(id)).findFirst().get();
    }

    @Override
    public ArrayList<Employee> findAll() throws IOException, InvocationTargetException, NoSuchMethodException,
                                                InstantiationException, IllegalAccessException {
        return employees;
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}