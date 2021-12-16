package ua.com.alevel.facade.impl;

import ua.com.alevel.dto.departmentemployee.DepartmentEmployeeRequestDTO;
import ua.com.alevel.dto.departmentemployee.DepartmentEmployeeResponseDTO;
import ua.com.alevel.entity.impl.Department;
import ua.com.alevel.entity.impl.DepartmentEmployee;
import ua.com.alevel.entity.impl.Employee;
import ua.com.alevel.facade.DepartmentEmployeeFacade;
import ua.com.alevel.service.DepartmentEmployeeService;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.service.impl.DepartmentEmployeeServiceImpl;
import ua.com.alevel.service.impl.DepartmentServiceImpl;
import ua.com.alevel.service.impl.EmployeeServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DepartmentEmployeeFacadeImpl implements DepartmentEmployeeFacade {

    private final DepartmentEmployeeService departmentEmployeeService = new DepartmentEmployeeServiceImpl();
    private final DepartmentService departmentService = new DepartmentServiceImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void create(DepartmentEmployeeRequestDTO departmentEmployeeRequestDTO)
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                   IllegalAccessException {
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        departmentEmployee.setDepartmentId(departmentEmployeeRequestDTO.getDepartmentId());
        departmentEmployee.setEmployeeId(departmentEmployeeRequestDTO.getEmployeeId());
        departmentEmployeeService.create(departmentEmployee);
    }

    @Override
    public void update(DepartmentEmployeeRequestDTO departmentEmployeeRequestDTO, String id)
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                   IllegalAccessException {
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        departmentEmployee.setId(id);
        departmentEmployee.setDepartmentId(departmentEmployeeRequestDTO.getDepartmentId());
        departmentEmployee.setEmployeeId(departmentEmployeeRequestDTO.getEmployeeId());
        departmentEmployeeService.update(departmentEmployee);
    }

    @Override
    public void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
                                         InstantiationException, IllegalAccessException {
        departmentEmployeeService.delete(id);
    }

    @Override
    public DepartmentEmployeeResponseDTO findById(String id) throws IOException {
        DepartmentEmployee departmentEmployee = departmentEmployeeService.findById(id);
        Department department = departmentService.findById(departmentEmployee.getDepartmentId());
        Employee employee = employeeService.findById(departmentEmployee.getEmployeeId());
        return new DepartmentEmployeeResponseDTO(departmentEmployee.getId(), department, employee);
    }

    @Override
    public ArrayList<DepartmentEmployeeResponseDTO> findAll()
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                   IllegalAccessException {
        ArrayList<DepartmentEmployeeResponseDTO> arrayList = new ArrayList<>();
        for (DepartmentEmployee departmentEmployee : departmentEmployeeService.findAll()) {
            DepartmentEmployeeResponseDTO byId = findById(departmentEmployee.getId());
            arrayList.add(byId);
        }
        return arrayList;
    }
}