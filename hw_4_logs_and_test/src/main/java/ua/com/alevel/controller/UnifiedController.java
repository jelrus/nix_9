package ua.com.alevel.controller;

import ua.com.alevel.controller.implementation.DepartmentControllerImpl;
import ua.com.alevel.controller.implementation.EmployeeControllerImpl;
import ua.com.alevel.controller.utils.input.InputUtils;
import ua.com.alevel.controller.utils.messages.MenuMessages;

import java.io.IOException;

public class UnifiedController {
    private static final DepartmentControllerImpl DEPARTMENT_CONTROLLER = new DepartmentControllerImpl();
    private static final EmployeeControllerImpl EMPLOYEE_CONTROLLER = new EmployeeControllerImpl();

    public static void run() throws IOException {
        MenuMessages.mainMenuText();
        String option = InputUtils.INPUT_READER.readLine();
        switch (option) {
            case "1" -> DEPARTMENT_CONTROLLER.run();
            case "2" -> EMPLOYEE_CONTROLLER.run();
            case "e" -> System.exit(0);
            default -> {
                System.out.println("Error");
                run();
            }
        }
        run();
    }
}