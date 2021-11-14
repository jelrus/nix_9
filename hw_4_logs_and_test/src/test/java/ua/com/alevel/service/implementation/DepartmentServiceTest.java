package ua.com.alevel.service.implementation;

import customcollections.DynamicArray;
import ua.com.alevel.entity.implementation.Department;
import org.junit.jupiter.api.*;
import ua.com.alevel.service.exceptions.DepartmentNotFoundException;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentServiceTest {

    private final static DepartmentServiceImpl departmentServiceImpl = new DepartmentServiceImpl();
    private final static int DEPARTMENT_SIZE = 30;

    @BeforeAll
    public static void populateDepartmentDB() {
        for (int i = 0; i < DEPARTMENT_SIZE; i++) {
            Department department = DepartmentGenerationUtil.generateDepartment(DepartmentGenerationUtil.NAME + i);
            departmentServiceImpl.create(department);
        }
        Assertions.assertEquals(DEPARTMENT_SIZE, departmentServiceImpl.findAll().size());
    }

    @Order(1)
    @Test
    public void shouldCreateDepartmentWhenNameIsEmpty() {
        Department department = new Department();
        department.setName(null);
        departmentServiceImpl.create(department);
        verifyDepartmentListWhenDepartmentsListIsNotEmpty(31);
    }

    @Order(2)
    @Test
    public void shouldDeleteDepartment() {
        String id = getRandomIdFromDepartmentList();
        departmentServiceImpl.delete(id);
        verifyDepartmentListWhenDepartmentsListIsNotEmpty(30);
    }

    @Order(3)
    @Test
    public void shouldFindDepartmentWhenIdIsRandom() {
        Department department = getRandomDepartmentFromDepartmentList(getRandomIdFromDepartmentList());
        Assertions.assertNotNull(department);
    }

    @Order(4)
    @Test
    public void shouldThrowExceptionWhenIdIsNotValid() {
        DepartmentNotFoundException thrown = Assertions.assertThrows(DepartmentNotFoundException.class,
                                                                     ()-> departmentServiceImpl.findById("invalidId"),
                                                             "DepartmentNotFound was expected");
        Assertions.assertEquals("Department not found!", thrown.getMessage());
    }

    @Order(5)
    @Test
    public void shouldUpdateDepartment() {
        String id = getRandomIdFromDepartmentList();
        Department department = getRandomDepartmentFromDepartmentList(id);
        department.setName("test name");
        departmentServiceImpl.update(department);
        department = departmentServiceImpl.findById(id);
        Assertions.assertEquals("test name", department.getName());
    }

    private void verifyDepartmentListWhenDepartmentsListIsNotEmpty(int size) {
        DynamicArray<Department> departments = departmentServiceImpl.findAll();
        Assertions.assertFalse(departments.containsOnlyNullObjects());
        Assertions.assertEquals(size, departmentServiceImpl.findAll().size());
    }

    private String getRandomIdFromDepartmentList() {
        Random random = new Random();
        int randomDepartmentIndex = random.nextInt(departmentServiceImpl.findAll().size());
        return departmentServiceImpl.findAll().get(randomDepartmentIndex).getId();
    }

    private Department getRandomDepartmentFromDepartmentList(String id){
        return departmentServiceImpl.findById(id);
    }
}