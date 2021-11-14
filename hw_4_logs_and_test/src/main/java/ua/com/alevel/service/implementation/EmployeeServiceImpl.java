package ua.com.alevel.service.implementation;

import customcollections.DynamicArray;
import ua.com.alevel.dao.EmployeeDAO;
import ua.com.alevel.dao.implementation.EmployeeDAOImpl;
import ua.com.alevel.service.exceptions.EmployeeNotFoundException;
import ua.com.alevel.entity.implementation.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public void create(Employee employee) {
        LOGGER_INFO.info("Employee creating started");
        employeeDAO.create(employee);
        LOGGER_INFO.info("Employee with " + employeeDAO.findById(employee.getId()) + " has been created");
    }

    @Override
    public void update(Employee employee) {
        LOGGER_INFO.info("Employee with id: " + employee.getId() + " update started");
        employeeDAO.update(employee);
        LOGGER_INFO.info("Employee has been changed to " + employeeDAO.findById(employee.getId()));
    }

    @Override
    public void delete(String id) {
        LOGGER_WARNING.warn("Employee deletion started");
        employeeDAO.delete(id);
        LOGGER_WARNING.warn("Employee with ID: " + id + " has been deleted");
    }

    @Override
    public Employee findById(String id) {
        Employee result = employeeDAO.findById(id);
        if (result == null){
            LOGGER_ERROR.error("Employee with id: " + id + " doesn't exist!");
            throw new EmployeeNotFoundException("Employee not found!");
        }
        return result;
    }

    @Override
    public DynamicArray<Employee> findAll() {
        return employeeDAO.findAll();
    }
}