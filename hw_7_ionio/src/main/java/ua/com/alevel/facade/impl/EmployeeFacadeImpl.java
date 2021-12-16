package ua.com.alevel.facade.impl;

import ua.com.alevel.dto.employee.EmployeeRequestDTO;
import ua.com.alevel.dto.employee.EmployeeResponseDTO;
import ua.com.alevel.entity.impl.Employee;
import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.service.impl.EmployeeServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EmployeeFacadeImpl implements EmployeeFacade {

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void create(EmployeeRequestDTO employeeRequestDTO) throws IOException, InvocationTargetException,
                                                                     NoSuchMethodException, InstantiationException,
                                                                     IllegalAccessException {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequestDTO.getFirstName());
        employee.setLastName(employeeRequestDTO.getLastName());
        employee.setAge(employeeRequestDTO.getAge());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setSalary(employeeRequestDTO.getSalary());
        employeeService.create(employee);
    }

    @Override
    public void update(EmployeeRequestDTO employeeRequestDTO, String id)
           throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                  IllegalAccessException {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(employeeRequestDTO.getFirstName());
        employee.setLastName(employeeRequestDTO.getLastName());
        employee.setAge(employeeRequestDTO.getAge());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setSalary(employeeRequestDTO.getSalary());
        employeeService.update(employee);
    }

    @Override
    public void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
                                         InstantiationException, IllegalAccessException {
        employeeService.delete(id);
    }

    @Override
    public EmployeeResponseDTO findById(String id) throws IOException {
        return new EmployeeResponseDTO(employeeService.findById(id));
    }

    @Override
    public ArrayList<EmployeeResponseDTO> findAll() throws IOException, InvocationTargetException, NoSuchMethodException,
                                                           InstantiationException, IllegalAccessException {
        return employeeService.findAll().stream()
                .map(EmployeeResponseDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}