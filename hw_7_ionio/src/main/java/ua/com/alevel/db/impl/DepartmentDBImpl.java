package ua.com.alevel.db.impl;

import csvio.CSVReaderWriter;
import ua.com.alevel.db.DepartmentDB;
import ua.com.alevel.entity.impl.Department;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

public class DepartmentDBImpl implements DepartmentDB {

    public static final String PATH_TO_DEP_CSV = "departments.csv";
    private static DepartmentDBImpl instance;
    private final ArrayList<Department> departments;

    private DepartmentDBImpl() {
        departments = new ArrayList<>();
    }

    public static DepartmentDBImpl getInstance() {
        if (instance == null) {
            instance = new DepartmentDBImpl();
        }
        return instance;
    }

    @Override
    public void create(Department department) throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        department.setId(generateId());
        department.setName(department.getName());
        departments.add(department);
        CSVReaderWriter.createRecord(PATH_TO_DEP_CSV, department, departments);
    }

    @Override
    public void update(Department department) throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        Department current = findById(department.getId());
        current.setName(department.getName());
        CSVReaderWriter.createRecord(PATH_TO_DEP_CSV, department, departments);
    }

    @Override
    public void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        Department department = findById(id);
        departments.remove(department);
        CSVReaderWriter.createRecord(PATH_TO_DEP_CSV, department, departments);
    }

    @Override
    public Department findById(String id) throws IOException {
        return departments.stream().filter(x -> x.getId().equals(id)).findFirst().get();
    }

    @Override
    public ArrayList<Department> findAll() throws IOException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        return departments;
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}