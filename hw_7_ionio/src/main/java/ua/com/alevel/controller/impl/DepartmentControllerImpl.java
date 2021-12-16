package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.DepartmentController;
import ua.com.alevel.controller.utils.input.InputUtils;
import ua.com.alevel.controller.utils.messages.ErrorMessages;
import ua.com.alevel.controller.utils.messages.MenuMessages;
import ua.com.alevel.dto.department.DepartmentRequestDTO;
import ua.com.alevel.dto.department.DepartmentResponseDTO;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.facade.impl.DepartmentFacadeImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class DepartmentControllerImpl implements DepartmentController {

    private final DepartmentFacade departmentFacade = new DepartmentFacadeImpl();

    @Override
    public void run() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        MenuMessages.departmentMenuText();
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
        String name = InputUtils.checkDepartmentName(InputUtils.INPUT_READER);
        DepartmentRequestDTO departmentRequestDTO = new DepartmentRequestDTO(name);
        departmentFacade.create(departmentRequestDTO);

    }

    @Override
    public void update() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        MenuMessages.printAllDepartments(departmentFacade);
        String id = InputUtils.checkDepartmentId(InputUtils.INPUT_READER);
        DepartmentResponseDTO departmentResponseDTO = departmentFacade.findById(id);
        String newName = InputUtils.checkDepartmentNewName(InputUtils.INPUT_READER, departmentResponseDTO.getName());
        DepartmentRequestDTO departmentRequestDTO = new DepartmentRequestDTO(newName);
        departmentFacade.update(departmentRequestDTO, id);
    }

    @Override
    public void delete() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        MenuMessages.printAllDepartments(departmentFacade);
        String id = InputUtils.checkDepartmentId(InputUtils.INPUT_READER);
        departmentFacade.delete(id);
    }

    @Override
    public void findById() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MenuMessages.printAllDepartments(departmentFacade);
        String id = InputUtils.checkDepartmentId(InputUtils.INPUT_READER);
        DepartmentResponseDTO departmentResponseDTO = departmentFacade.findById(id);
        MenuMessages.printDepartment(departmentResponseDTO);
    }

    @Override
    public void findAll() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        if (!departmentFacade.findAll().isEmpty()) {
            MenuMessages.printAllDepartments(departmentFacade);
        } else {
            ErrorMessages.printDepartmentsListIsEmpty();
        }
    }
}