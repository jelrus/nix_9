package ua.com.alevel.controller.implementation;

import ua.com.alevel.controller.DepartmentController;
import ua.com.alevel.controller.UnifiedController;
import ua.com.alevel.controller.utils.input.InputUtils;
import ua.com.alevel.controller.utils.messages.ErrorMessages;
import ua.com.alevel.controller.utils.messages.MenuMessages;
import ua.com.alevel.entity.implementation.Department;
import ua.com.alevel.service.implementation.DepartmentServiceImpl;

import java.io.IOException;
import java.util.Locale;

public class DepartmentControllerImpl implements DepartmentController {

    private static final DepartmentServiceImpl DEPARTMENT_SERVICE_IMPL = new DepartmentServiceImpl();

    @Override
    public void run() throws IOException {
        MenuMessages.departmentMenuText();
        MenuMessages.optionInput();
        String option = InputUtils.INPUT_READER.readLine();
        switch (option) {
            case "1" -> create();
            case "2" -> update();
            case "3" -> delete();
            case "4" -> findById();
            case "5" -> findAll();
            default -> {
                ErrorMessages.optionError();
                run();
            }
        }
        returnOption();
    }

    public void returnOption() throws IOException {
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
    public void create() throws IOException {
        String name = InputUtils.checkDepartmentName(InputUtils.INPUT_READER);
        Department department = new Department();
        department.setName(name);
        DEPARTMENT_SERVICE_IMPL.create(department);
    }

    @Override
    public void update() throws IOException {
        String id = InputUtils.checkDepartmentId(InputUtils.INPUT_READER);
        Department department = DEPARTMENT_SERVICE_IMPL.findById(id);
        String newName = InputUtils.checkDepartmentNewName(InputUtils.INPUT_READER, department.getName());
        department.setName(newName);
        DEPARTMENT_SERVICE_IMPL.update(department);
    }

    @Override
    public void delete() throws IOException {
        String id = InputUtils.checkDepartmentId(InputUtils.INPUT_READER);
        DEPARTMENT_SERVICE_IMPL.delete(id);
    }

    @Override
    public void findById() throws IOException {
        String id = InputUtils.checkDepartmentId(InputUtils.INPUT_READER);
        Department department = DEPARTMENT_SERVICE_IMPL.findById(id);
        MenuMessages.printDepartment(department);
    }

    @Override
    public void findAll() {
        if (!DEPARTMENT_SERVICE_IMPL.findAll().containsOnlyNullObjects()) {
            MenuMessages.printAllDepartments(DEPARTMENT_SERVICE_IMPL);
        } else {
            ErrorMessages.printDepartmentsListIsEmpty();
        }
    }
}