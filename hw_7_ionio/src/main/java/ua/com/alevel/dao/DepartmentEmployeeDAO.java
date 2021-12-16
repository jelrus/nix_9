package ua.com.alevel.dao;

import ua.com.alevel.entity.impl.DepartmentEmployee;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface DepartmentEmployeeDAO extends BaseDAO<DepartmentEmployee> {

    void deleteDepartments(String departmentId) throws IOException, InvocationTargetException, NoSuchMethodException,
                                                       InstantiationException, IllegalAccessException;

    void deleteEmployees(String employeeId) throws IOException, InvocationTargetException, NoSuchMethodException,
                                                   InstantiationException, IllegalAccessException;
}