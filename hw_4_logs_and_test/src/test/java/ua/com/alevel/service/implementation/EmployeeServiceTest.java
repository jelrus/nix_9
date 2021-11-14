package ua.com.alevel.service.implementation;

import org.junit.jupiter.api.*;
import customcollections.DynamicArray;
import ua.com.alevel.entity.implementation.Department;
import ua.com.alevel.entity.implementation.Employee;
import ua.com.alevel.service.exceptions.EmployeeNotFoundException;

import java.math.BigDecimal;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTest {

    private final static EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
    private final static DepartmentServiceImpl departmentServiceImpl = new DepartmentServiceImpl();
    private final static int EMPLOYEES_SIZE = 40;

    @BeforeAll
    public static void populateEmployeesDB() {
        for (int i = 0; i < EMPLOYEES_SIZE; i++) {
            Employee employee = EmployeeGenerationUtil.generateEmployee(EmployeeGenerationUtil.FIRST_NAME + i,
                                                                         EmployeeGenerationUtil.LAST_NAME + i,
                                                                             EmployeeGenerationUtil.AGE + i,
                                                                            EmployeeGenerationUtil.EMAIL + i,
                                                                                  EmployeeGenerationUtil.SALARY,
                                                                                  departmentServiceImpl.findAll());
            employeeServiceImpl.create(employee);
        }
        Assertions.assertEquals(EMPLOYEES_SIZE, employeeServiceImpl.findAll().size());
    }

    @Order(1)
    @Test
    public void shouldCreateEmployeeWithEmptyFields() {
        Employee employee = new Employee();
        employee.setFirstName(null);
        employee.setLastName(null);
        employee.setAge(0);
        employee.setEmail(null);
        employee.setSalary(null);
        DynamicArray<Department> departments = new DynamicArray<>();
        employee.setDepartments(departments);
        employeeServiceImpl.create(employee);
        verifyEmployeeListWhenEmployeesListIsNotEmpty(41);
    }

    @Order(2)
    @Test
    public void shouldDeleteEmployee() {
        String id = getRandomIdFromEmployeeList();
        employeeServiceImpl.delete(id);
        verifyEmployeeListWhenEmployeesListIsNotEmpty(40);
    }

    @Order(3)
    @Test
    public void shouldFindEmployeeWhenIdIsRandom() {
        Employee employee = getRandomEmployeeFromEmployeeList(getRandomIdFromEmployeeList());
        Assertions.assertNotNull(employee);
    }

    @Order(4)
    @Test
    public void shouldThrowExceptionWhenIdIsNotValid() {
        EmployeeNotFoundException thrown = Assertions.assertThrows(EmployeeNotFoundException.class,
                                                                   () -> employeeServiceImpl.findById("invalidId"),
                                                           "EmployeeNotFound was expected");
        Assertions.assertEquals("Employee not found!", thrown.getMessage());
    }

    @Order(5)
    @Test
    public void shouldUpdateEmployee() {
        String id = getRandomIdFromEmployeeList();
        Employee employee = getRandomEmployeeFromEmployeeList(id);
        employee.setFirstName("new first name");
        employee.setLastName("new last name");
        employee.setAge(35);
        employee.setEmail("new email");
        employee.setSalary(new BigDecimal(100));
        DynamicArray<Department> newDepartments = new DynamicArray<>();
        for (int i = 0; i < 5; i++) {
            Department department = new Department();
            department.setId(String.valueOf(i));
            department.setName("name" + i);
            newDepartments.add(department);
        }
        employee.setDepartments(newDepartments);
        employeeServiceImpl.update(employee);
        Assertions.assertEquals("new first name", employee.getFirstName());
        Assertions.assertEquals("new last name", employee.getLastName());
        Assertions.assertEquals(35, employee.getAge());
        Assertions.assertEquals("new email", employee.getEmail());
        Assertions.assertEquals(new BigDecimal(100), employee.getSalary());
        Assertions.assertEquals(newDepartments, employee.getDepartments());
    }

    private void verifyEmployeeListWhenEmployeesListIsNotEmpty(int size) {
        DynamicArray<Employee> employees = employeeServiceImpl.findAll();
        Assertions.assertFalse(employees.containsOnlyNullObjects());
        Assertions.assertEquals(size, employeeServiceImpl.findAll().size());
    }

    private String getRandomIdFromEmployeeList() {
        Random random = new Random();
        int randomEmployeeIndex = random.nextInt(employeeServiceImpl.findAll().size());
        return employeeServiceImpl.findAll().get(randomEmployeeIndex).getId();
    }


    private Employee getRandomEmployeeFromEmployeeList(String id) {
        return employeeServiceImpl.findById(id);
    }
}