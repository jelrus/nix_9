package ua.com.alevel.service.implementation;

import customcollections.DynamicArray;
import ua.com.alevel.entity.implementation.Department;
import ua.com.alevel.entity.implementation.Employee;

import java.math.BigDecimal;

public class EmployeeGenerationUtil {

    public static final String FIRST_NAME = "first name";
    public static final String LAST_NAME = "last name";
    public static final int AGE = 100;
    public static final String EMAIL = "email@home";
    public static final BigDecimal SALARY = new BigDecimal(1000);
    public static final DynamicArray<Department> DEPARTMENTS = new DynamicArray<>();

    public static Employee generateEmployee(String firstName, String lastName, int age, String email,
                                            BigDecimal salary, DynamicArray<Department> departments){
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAge(age);
        employee.setEmail(email);
        employee.setSalary(salary);
        employee.setDepartments(departments);
        return employee;
    }
}