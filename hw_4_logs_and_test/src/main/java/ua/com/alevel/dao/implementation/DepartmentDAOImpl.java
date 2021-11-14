package ua.com.alevel.dao.implementation;

import customcollections.DynamicArray;
import ua.com.alevel.dao.DepartmentDAO;
import ua.com.alevel.db.DepartmentDB;
import ua.com.alevel.db.implementation.DepartmentDBImpl;
import ua.com.alevel.entity.implementation.Department;

public class DepartmentDAOImpl implements DepartmentDAO {

    private final DepartmentDB departmentDB = DepartmentDBImpl.getInstance();

    @Override
    public void create(Department department) {
        departmentDB.create(department);
    }

    @Override
    public void update(Department department) {
        departmentDB.update(department);
    }

    @Override
    public void delete(String id) {
        departmentDB.delete(id);
    }

    @Override
    public Department findById(String id) {
        return departmentDB.findById(id);
    }

    @Override
    public DynamicArray<Department> findAll() {
        return departmentDB.findAll();
    }
}