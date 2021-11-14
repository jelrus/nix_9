package ua.com.alevel.db.implementation;

import customcollections.DynamicArray;
import ua.com.alevel.db.DepartmentDB;
import ua.com.alevel.entity.implementation.Department;

import java.util.Objects;
import java.util.UUID;

public class DepartmentDBImpl implements DepartmentDB {

    private static DepartmentDBImpl instance;
    private final DynamicArray<Department> departments;

    private DepartmentDBImpl() {
        departments = new DynamicArray<>();
    }

    public static DepartmentDBImpl getInstance() {
        if (instance == null) {
            instance = new DepartmentDBImpl();
        }
        return instance;
    }

    @Override
    public void create(Department department) {
        department.setId(generateId());
        department.setName(department.getName());
        departments.add(department);
    }

    @Override
    public void update(Department department) {
        Department current = findById(department.getId());
        current.setName(department.getName());
    }

    @Override
    public void delete(String id) {
        for (int i = 0; i < departments.size(); i++) {
            if (Objects.equals(departments.get(i).getId(), id)) {
                departments.remove(i);
            }
        }
    }

    @Override
    public Department findById(String id) {
        Department result = null;
        for (int i = 0; i < departments.size(); i++) {
            if (Objects.equals(departments.get(i).getId(), id)) {
                result = departments.get(i);
            }
        }
        return result;
    }

    @Override
    public DynamicArray<Department> findAll() {
        return departments;
    }

    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}