package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.utils.input.InputUtils;
import ua.com.alevel.controller.utils.messages.ErrorMessages;
import ua.com.alevel.controller.utils.messages.MenuMessages;
import ua.com.alevel.db.impl.DepartmentDBImpl;
import ua.com.alevel.db.impl.DepartmentEmployeeDBImpl;
import ua.com.alevel.db.impl.EmployeeDBImpl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class UnifiedController {

    private static final DepartmentControllerImpl DEPARTMENT_CONTROLLER = new DepartmentControllerImpl();
    private static final EmployeeControllerImpl EMPLOYEE_CONTROLLER = new EmployeeControllerImpl();
    private static final DepartmentEmployeeControllerImpl DEPARTMENT_EMPLOYEE_CONTROLLER = new DepartmentEmployeeControllerImpl();

    public static void run() throws IOException, InvocationTargetException, NoSuchMethodException,
                                    InstantiationException, IllegalAccessException {
        MenuMessages.mainMenuText();
        String option = InputUtils.INPUT_READER.readLine();
        switch (option) {
            case "1" -> DEPARTMENT_CONTROLLER.run();
            case "2" -> EMPLOYEE_CONTROLLER.run();
            case "3" -> DEPARTMENT_EMPLOYEE_CONTROLLER.run();
            case "e" -> System.exit(0);
            default -> {
                ErrorMessages.optionError();
                run();
            }
        }
        run();
    }

    public static void wipePreviousRecords() {
        File depFile = new File(DepartmentDBImpl.PATH_TO_DEP_CSV);
        if (depFile.exists()) {
            depFile.delete();
        }
        File empFile = new File(EmployeeDBImpl.PATH_TO_EMP_CSV);
        if (empFile.exists()) {
            empFile.delete();
        }
        File depEmpFile = new File(DepartmentEmployeeDBImpl.PATH_TO_DEP_EMP_CSV);
        if (depEmpFile.exists()) {
            depEmpFile.delete();
        }
    }
}