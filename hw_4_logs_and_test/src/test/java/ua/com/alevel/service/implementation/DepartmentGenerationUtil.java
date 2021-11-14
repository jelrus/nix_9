package ua.com.alevel.service.implementation;

import ua.com.alevel.entity.implementation.Department;

public class DepartmentGenerationUtil {

    public static final String NAME = "name";

    public static Department generateDepartment(String name){
        Department department = new Department();
        department.setName(name);
        return department;
    }
}