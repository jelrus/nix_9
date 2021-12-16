package ua.com.alevel.facade.impl;

import ua.com.alevel.dto.department.DepartmentRequestDTO;
import ua.com.alevel.dto.department.DepartmentResponseDTO;
import ua.com.alevel.entity.impl.Department;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.impl.DepartmentServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DepartmentFacadeImpl implements DepartmentFacade {

    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public void create(DepartmentRequestDTO departmentRequestDTO)
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                   IllegalAccessException {
        Department department = new Department();
        department.setName(departmentRequestDTO.getName());
        departmentService.create(department);
    }

    @Override
    public void update(DepartmentRequestDTO departmentRequestDTO, String id)
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                   IllegalAccessException {
        Department department = new Department();
        department.setId(id);
        department.setName(departmentRequestDTO.getName());
        departmentService.update(department);
    }

    @Override
    public void delete(String id) throws IOException, InvocationTargetException, NoSuchMethodException,
                                         InstantiationException, IllegalAccessException {
        departmentService.delete(id);
    }

    @Override
    public DepartmentResponseDTO findById(String id) throws IOException {
        return new DepartmentResponseDTO(departmentService.findById(id));
    }

    @Override
    public ArrayList<DepartmentResponseDTO> findAll()
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                   IllegalAccessException {
        return departmentService.findAll().stream()
                .map(DepartmentResponseDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}