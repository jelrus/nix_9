package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.DepartmentDAO;
import ua.com.alevel.db.impl.DepartmentDBImpl;
import ua.com.alevel.entity.impl.Department;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DepartmentDAOImpl implements DepartmentDAO {

    private final DepartmentDBImpl departmentDB = DepartmentDBImpl.getInstance();

    @Override
    public void create(Department department) throws IOException, InvocationTargetException, NoSuchMethodException,
                                                     InstantiationException, IllegalAccessException {
        departmentDB.create(department);
    }

    @Override
    public void update(Department department) throws IOException, InvocationTargetException, NoSuchMethodException,
                                                     InstantiationException, IllegalAccessException {
        departmentDB.update(department);
    }

    @Override
    public void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
                                         InstantiationException, IllegalAccessException {
        departmentDB.delete(id);
    }

    @Override
    public Department findById(String id) throws IOException {
        return departmentDB.findById(id);
    }

    @Override
    public ArrayList<Department> findAll() throws IOException, InvocationTargetException, NoSuchMethodException,
                                                  InstantiationException, IllegalAccessException {
        return departmentDB.findAll();
    }
}