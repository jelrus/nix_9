package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.utils.input.InputUtils;
import ua.com.alevel.controller.utils.messages.ErrorMessages;
import ua.com.alevel.controller.utils.messages.MenuMessages;
import ua.com.alevel.db.impl.DepartmentDBImpl;
import ua.com.alevel.db.impl.DepartmentEmployeeDBImpl;
import ua.com.alevel.db.impl.EmployeeDBImpl;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    private static void createEmptyDepFile() throws IOException {
        Path userPath = Paths.get(System.getProperty("user.dir"));
        String uPath = userPath.toString();
        if (uPath.contains("hw_7_ionio")) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(uPath + "\\" + DepartmentDBImpl.PATH_TO_DEP_CSV))) {
                bw.write("");
            }
        } else {
            uPath = uPath + "\\hw_7_ionio";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(uPath + "\\" + DepartmentDBImpl.PATH_TO_DEP_CSV))) {
                bw.write("");
            }
        }
    }

    private static void createEmptyEmpFile() throws IOException {
        Path userPath = Paths.get(System.getProperty("user.dir"));
        String uPath = userPath.toString();
        if (uPath.contains("hw_7_ionio")) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(uPath + "\\" + EmployeeDBImpl.PATH_TO_EMP_CSV))) {
                bw.write("");
            }
        } else {
            uPath = uPath + "\\hw_7_ionio";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(uPath + "\\" + EmployeeDBImpl.PATH_TO_EMP_CSV))) {
                bw.write("");
            }
        }
    }

    private static void createEmptyDepEmpFile() throws IOException {
        Path userPath = Paths.get(System.getProperty("user.dir"));
        String uPath = userPath.toString();
        if (uPath.contains("hw_7_ionio")) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(uPath + "\\" + DepartmentEmployeeDBImpl.PATH_TO_DEP_EMP_CSV))) {
                bw.write("");
            }
        } else {
            uPath = uPath + "\\hw_7_ionio";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(uPath + "\\" + DepartmentEmployeeDBImpl.PATH_TO_DEP_EMP_CSV))) {
                bw.write("");
            }
        }
    }

    public static void createNewFiles() throws IOException {
        createEmptyDepFile();
        createEmptyEmpFile();
        createEmptyDepEmpFile();
    }
}