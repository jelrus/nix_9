package ua.com.alevel.service.implementation;

import customcollections.DynamicArray;
import ua.com.alevel.dao.DepartmentDAO;
import ua.com.alevel.dao.EmployeeDAO;
import ua.com.alevel.dao.implementation.DepartmentDAOImpl;
import ua.com.alevel.dao.implementation.EmployeeDAOImpl;
import ua.com.alevel.service.exceptions.DepartmentNotFoundException;
import ua.com.alevel.entity.implementation.Department;
import ua.com.alevel.entity.implementation.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.service.DepartmentService;

import java.util.Objects;

public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final DepartmentDAO departmentDAO = new DepartmentDAOImpl();
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();


    @Override
    public void create(Department department) {
        LOGGER_INFO.info("Department creating started");
        departmentDAO.create(department);
        LOGGER_INFO.info("Department with " + departmentDAO.findById(department.getId()) + " has been created");
    }

    @Override
    public void update(Department department) {
        LOGGER_INFO.info("Department with id: " + department.getId() + " update started");
        departmentDAO.update(department);
        LOGGER_INFO.info("Department has been changed to " + departmentDAO.findById(department.getId()));
    }

    @Override
    public void delete(String id) {
        LOGGER_WARNING.warn("Department deletion started");
        deleteFromEmployees(id);
        departmentDAO.delete(id);
        LOGGER_WARNING.warn("Department with ID: " + id + " has been deleted");
    }

    @Override
    public Department findById(String id) {
        Department result = departmentDAO.findById(id);
        if (result == null) {
            LOGGER_ERROR.error("Department with id: " + id + " doesn't exist!");
            throw new DepartmentNotFoundException("Department not found!");
        }
        return result;
    }

    @Override
    public DynamicArray<Department> findAll() {
        return departmentDAO.findAll();
    }

    private void deleteFromEmployees(String id) {
        DynamicArray<Employee> employees = employeeDAO.findAll();
        for (int i = 0; i < employees.size(); i++) {
            for (int j = 0; j < employees.get(i).getDepartments().size(); j++) {
                if (Objects.equals(employees.get(i).getDepartments().get(j).getId(), id)) {
                    employees.get(i).getDepartments().remove(j);
                }
            }
        }
    }
}