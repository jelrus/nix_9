package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.DepartmentEmployeeController;
import ua.com.alevel.controller.utils.input.InputUtils;
import ua.com.alevel.controller.utils.messages.ErrorMessages;
import ua.com.alevel.controller.utils.messages.MenuMessages;
import ua.com.alevel.dto.departmentemployee.DepartmentEmployeeRequestDTO;
import ua.com.alevel.dto.departmentemployee.DepartmentEmployeeResponseDTO;
import ua.com.alevel.facade.DepartmentEmployeeFacade;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.facade.impl.DepartmentEmployeeFacadeImpl;
import ua.com.alevel.facade.impl.DepartmentFacadeImpl;
import ua.com.alevel.facade.impl.EmployeeFacadeImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class DepartmentEmployeeControllerImpl implements DepartmentEmployeeController {

    private final DepartmentFacade departmentFacade = new DepartmentFacadeImpl();
    private final EmployeeFacade employeeFacade = new EmployeeFacadeImpl();
    private final DepartmentEmployeeFacade departmentEmployeeFacade = new DepartmentEmployeeFacadeImpl();

    @Override
    public void run() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                             IllegalAccessException {
        MenuMessages.departmentEmployeeMenuText();
        MenuMessages.optionInput();
        String option = InputUtils.INPUT_READER.readLine();
        switch (option) {
            case "1" -> create();
            case "2" -> update();
            case "3" -> delete();
            case "4" -> findById();
            case "5" -> findAll();
            case "e" -> UnifiedController.run();
            default -> {
                ErrorMessages.optionError();
                run();
            }
        }
        returnOption();
    }

    public void returnOption() throws IOException, InvocationTargetException, NoSuchMethodException,
                                      InstantiationException, IllegalAccessException {
        MenuMessages.returnBackOption();
        String option = InputUtils.INPUT_READER.readLine().toUpperCase(Locale.ROOT);
        switch (option) {
            case "Y" -> UnifiedController.run();
            case "N" -> run();
            default -> {
                ErrorMessages.optionError();
                returnOption();
            }
        }
    }

    @Override
    public void create() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                                IllegalAccessException {
        MenuMessages.printAllDepartments(departmentFacade);
        String depId = InputUtils.checkDepartmentId(InputUtils.INPUT_READER);
        MenuMessages.printAllEmployees(employeeFacade);
        String empId = InputUtils.checkEmployeeId(InputUtils.INPUT_READER);
        DepartmentEmployeeRequestDTO departmentEmployeeRequestDTO = new DepartmentEmployeeRequestDTO(depId, empId);
        departmentEmployeeFacade.create(departmentEmployeeRequestDTO);
    }

    @Override
    public void update() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                                IllegalAccessException {
        MenuMessages.relationId();
        String relId = InputUtils.checkRelationId(InputUtils.INPUT_READER);
        MenuMessages.printAllDepartments(departmentFacade);
        String depId = InputUtils.checkDepartmentId(InputUtils.INPUT_READER);
        MenuMessages.printAllEmployees(employeeFacade);
        String empId = InputUtils.checkEmployeeId(InputUtils.INPUT_READER);
        DepartmentEmployeeRequestDTO departmentEmployeeRequestDTO = new DepartmentEmployeeRequestDTO(depId, empId);
        departmentEmployeeFacade.update(departmentEmployeeRequestDTO, relId);
    }

    @Override
    public void delete() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                                IllegalAccessException {
        String relId = InputUtils.checkRelationId(InputUtils.INPUT_READER);
        departmentEmployeeFacade.delete(relId);
    }

    @Override
    public void findById() throws IOException {
        MenuMessages.relationId();
        String relId = InputUtils.checkRelationId(InputUtils.INPUT_READER);
        DepartmentEmployeeResponseDTO departmentEmployeeResponseDTO = departmentEmployeeFacade.findById(relId);
        MenuMessages.printRelation(departmentEmployeeResponseDTO);
    }

    @Override
    public void findAll() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                                 IllegalAccessException {
        if (!departmentEmployeeFacade.findAll().isEmpty()) {
            MenuMessages.printAllRelations(departmentEmployeeFacade);
        } else {
            ErrorMessages.printRelationsListIsEmpty();
        }
    }
}