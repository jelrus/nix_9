package ua.com.alevel.dao.impl;

import csvio.CSVReaderWriter;
import ua.com.alevel.dao.DepartmentEmployeeDAO;
import ua.com.alevel.db.impl.DepartmentEmployeeDBImpl;
import ua.com.alevel.entity.impl.DepartmentEmployee;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentEmployeeDAOImpl implements DepartmentEmployeeDAO {

    private final DepartmentEmployeeDBImpl departmentEmployeeDB = DepartmentEmployeeDBImpl.getInstance();

    @Override
    public void create(DepartmentEmployee departmentEmployee) throws IOException, InvocationTargetException,
                                                                     NoSuchMethodException, InstantiationException,
                                                                     IllegalAccessException {
        departmentEmployeeDB.create(departmentEmployee);
    }

    @Override
    public void update(DepartmentEmployee departmentEmployee) throws IOException, InvocationTargetException,
                                                                     NoSuchMethodException, InstantiationException,
                                                                     IllegalAccessException {
        departmentEmployeeDB.update(departmentEmployee);
    }

    @Override
    public void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
                                         InstantiationException, IllegalAccessException {
        departmentEmployeeDB.delete(id);
    }

    @Override
    public DepartmentEmployee findById(String id) throws IOException {
        return departmentEmployeeDB.findById(id);
    }

    @Override
    public ArrayList<DepartmentEmployee> findAll() throws IOException, InvocationTargetException, NoSuchMethodException,
                                                          InstantiationException, IllegalAccessException {
        return departmentEmployeeDB.findAll();
    }


    @Override
    public void deleteDepartments(String departmentId) throws IOException, InvocationTargetException,
                                                              NoSuchMethodException, InstantiationException,
                                                              IllegalAccessException {
        List<DepartmentEmployee> depEmpRemoveList = new ArrayList<>();
        departmentEmployeeDB.findAll().stream().filter(x -> x.getDepartmentId().equals(departmentId))
                .forEach(depEmpRemoveList::add);
        ArrayList<DepartmentEmployee> filter =
                (ArrayList<DepartmentEmployee>) CSVReaderWriter.readAllObjects(DepartmentEmployeeDBImpl.PATH_TO_DEP_EMP_CSV,
                        DepartmentEmployee.class);
        filter.removeAll(depEmpRemoveList);
        CSVReaderWriter.createRecord(DepartmentEmployeeDBImpl.PATH_TO_DEP_EMP_CSV, new DepartmentEmployee(), filter);
        departmentEmployeeDB.findAll().removeAll(depEmpRemoveList);
    }

    @Override
    public void deleteEmployees(String employeeId) throws IOException, InvocationTargetException, NoSuchMethodException,
                                                          InstantiationException, IllegalAccessException {
        List<DepartmentEmployee> depEmpRemoveList = new ArrayList<>();
        departmentEmployeeDB.findAll().stream().filter(x -> x.getEmployeeId().equals(employeeId))
                .forEach(depEmpRemoveList::add);
        ArrayList<DepartmentEmployee> filter =
                (ArrayList<DepartmentEmployee>) CSVReaderWriter.readAllObjects(DepartmentEmployeeDBImpl.PATH_TO_DEP_EMP_CSV,
                        DepartmentEmployee.class);
        filter.removeAll(depEmpRemoveList);
        CSVReaderWriter.createRecord(DepartmentEmployeeDBImpl.PATH_TO_DEP_EMP_CSV, new DepartmentEmployee(), filter);
        departmentEmployeeDB.findAll().removeAll(depEmpRemoveList);
    }
}