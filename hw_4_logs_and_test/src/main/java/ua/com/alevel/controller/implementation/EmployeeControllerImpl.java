package ua.com.alevel.controller.implementation;

import ua.com.alevel.controller.EmployeeController;
import ua.com.alevel.controller.UnifiedController;
import ua.com.alevel.controller.utils.input.InputUtils;
import ua.com.alevel.controller.utils.messages.ErrorMessages;
import ua.com.alevel.controller.utils.messages.MenuMessages;
import customcollections.DynamicArray;
import ua.com.alevel.entity.implementation.Department;
import ua.com.alevel.entity.implementation.Employee;
import ua.com.alevel.service.implementation.DepartmentServiceImpl;
import ua.com.alevel.service.implementation.EmployeeServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

public class EmployeeControllerImpl implements EmployeeController {

    private static final EmployeeServiceImpl EMPLOYEE_SERVICE_IMPL = new EmployeeServiceImpl();
    private static final DepartmentServiceImpl DEPARTMENT_SERVICE_IMPL = new DepartmentServiceImpl();

    @Override
    public void run() throws IOException {
        MenuMessages.employeeMenuText();
        String option = InputUtils.INPUT_READER.readLine();
        switch (option) {
            case "1" -> create();
            case "2" -> update();
            case "3" -> delete();
            case "4" -> findById();
            case "5" -> findAll();
            default -> {
                ErrorMessages.optionError();
                run();
            }
        }
        returnOption();
    }

    public void returnOption() throws IOException {
        MenuMessages.returnBackOption();
        String option = InputUtils.INPUT_READER.readLine().toUpperCase(Locale.ROOT);
        switch (option) {
            case "Y" -> UnifiedController.run();
            case "N" -> run();
            default -> {
                ErrorMessages.optionError();
                returnOption();
            }
        }
    }

    @Override
    public void create() throws IOException {
        String firstName = InputUtils.checkEmployeeFirstName(InputUtils.INPUT_READER);
        String lastName = InputUtils.checkEmployeeLastName(InputUtils.INPUT_READER);
        int age = InputUtils.checkEmployeeAge(InputUtils.INPUT_READER);
        String email = InputUtils.checkEmployeeEmail(InputUtils.INPUT_READER);
        BigDecimal salary = InputUtils.checkEmployeeSalary(InputUtils.INPUT_READER);
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAge(age);
        employee.setEmail(email);
        employee.setSalary(salary);
        int number = InputUtils.checkDepartmentsNumber(InputUtils.INPUT_READER);
        DynamicArray<Department> departments = InputUtils.addDepartmentsToNewEmployee(InputUtils.INPUT_READER,
                                                                                      DEPARTMENT_SERVICE_IMPL, number);
        employee.setDepartments(departments);
        EMPLOYEE_SERVICE_IMPL.create(employee);
    }

    @Override
    public void update() throws IOException {
        MenuMessages.empIdInputMessage();
        String id = InputUtils.INPUT_READER.readLine();
        Employee employee = EMPLOYEE_SERVICE_IMPL.findById(id);
        String newFirstName = InputUtils.checkEmployeeNewFirstName(InputUtils.INPUT_READER, employee.getFirstName());
        String newLastName = InputUtils.checkEmployeeNewLastName(InputUtils.INPUT_READER, employee.getLastName());
        int newAge = InputUtils.checkEmployeeNewAge(InputUtils.INPUT_READER, employee.getAge());
        String newEmail = InputUtils.checkEmployeeNewEmail(InputUtils.INPUT_READER, employee.getEmail());
        BigDecimal newSalary = InputUtils.checkEmployeeNewSalary(InputUtils.INPUT_READER, employee.getSalary());
        DynamicArray<Department> newDepartments = InputUtils.removeOrAddDep(InputUtils.INPUT_READER,
                                                                            DEPARTMENT_SERVICE_IMPL,
                                                                            EMPLOYEE_SERVICE_IMPL, id);
        employee.setFirstName(newFirstName);
        employee.setLastName(newLastName);
        employee.setAge(newAge);
        employee.setEmail(newEmail);
        employee.setSalary(newSalary);
        employee.setDepartments(newDepartments);
        EMPLOYEE_SERVICE_IMPL.update(employee);
    }

    @Override
    public void delete() throws IOException {
        String id = InputUtils.checkEmployeeId(InputUtils.INPUT_READER);
        EMPLOYEE_SERVICE_IMPL.delete(id);
    }

    @Override
    public void findById() throws IOException {
        String id = InputUtils.checkEmployeeId(InputUtils.INPUT_READER);
        Employee employee = EMPLOYEE_SERVICE_IMPL.findById(id);
        MenuMessages.printEmployee(employee);
    }

    @Override
    public void findAll() {
        if (!EMPLOYEE_SERVICE_IMPL.findAll().containsOnlyNullObjects()) {
            MenuMessages.printAllEmployees(EMPLOYEE_SERVICE_IMPL);
        } else {
            ErrorMessages.printEmployeeListIsEmpty();
        }
    }
}