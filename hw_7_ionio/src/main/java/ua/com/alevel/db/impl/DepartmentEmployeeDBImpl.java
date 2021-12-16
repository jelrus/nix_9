package ua.com.alevel.db.impl;

import csvio.CSVReaderWriter;
import ua.com.alevel.db.DepartmentEmployeeDB;
import ua.com.alevel.entity.impl.DepartmentEmployee;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.UUID;

public class DepartmentEmployeeDBImpl implements DepartmentEmployeeDB {

    public static final String PATH_TO_DEP_EMP_CSV = "hw_7_ionio/departments_employees.csv";
    private static DepartmentEmployeeDBImpl instance;
    private final ArrayList<DepartmentEmployee> departmentsEmployees;

    private DepartmentEmployeeDBImpl() {
        departmentsEmployees = new ArrayList<>();
    }

    public static DepartmentEmployeeDBImpl getInstance() {
        if (instance == null) {
            instance = new DepartmentEmployeeDBImpl();
        }
        return instance;
    }

    @Override
    public void create(DepartmentEmployee departmentEmployee) throws IOException, InvocationTargetException,
                                                                     NoSuchMethodException, InstantiationException,
                                                                     IllegalAccessException {
        departmentEmployee.setId(generateId());
        departmentsEmployees.add(departmentEmployee);
        CSVReaderWriter.createRecord(PATH_TO_DEP_EMP_CSV, departmentEmployee, departmentsEmployees);
    }

    @Override
    public void update(DepartmentEmployee departmentEmployee) throws IOException, InvocationTargetException,
                                                                     NoSuchMethodException, InstantiationException,
                                                                     IllegalAccessException {
        DepartmentEmployee current = findById(departmentEmployee.getId());
        current.setDepartmentId(departmentEmployee.getDepartmentId());
        current.setEmployeeId(departmentEmployee.getEmployeeId());
        CSVReaderWriter.createRecord(PATH_TO_DEP_EMP_CSV, departmentEmployee, departmentsEmployees);
    }

    @Override
    public void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
                                         InstantiationException, IllegalAccessException {
        DepartmentEmployee departmentEmployee = findById(id);
        departmentsEmployees.remove(departmentEmployee);
        CSVReaderWriter.createRecord(PATH_TO_DEP_EMP_CSV, departmentEmployee, departmentsEmployees);
    }

    @Override
    public DepartmentEmployee findById(String id) {
        return departmentsEmployees.stream().filter(x -> x.getId().equals(id)).findFirst().get();
    }

    @Override
    public ArrayList<DepartmentEmployee> findAll() throws IOException, InvocationTargetException, NoSuchMethodException,
                                                          InstantiationException, IllegalAccessException {
        return (ArrayList<DepartmentEmployee>) CSVReaderWriter.readAllObjects(PATH_TO_DEP_EMP_CSV, DepartmentEmployee.class);
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}