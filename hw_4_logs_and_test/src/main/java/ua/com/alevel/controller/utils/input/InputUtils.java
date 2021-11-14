package ua.com.alevel.controller.utils.input;

import customcollections.DynamicArray;
import ua.com.alevel.entity.implementation.Department;
import ua.com.alevel.entity.implementation.Employee;
import ua.com.alevel.service.implementation.DepartmentServiceImpl;
import ua.com.alevel.service.implementation.EmployeeServiceImpl;
import ua.com.alevel.controller.utils.messages.ErrorMessages;
import ua.com.alevel.controller.utils.messages.MenuMessages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Locale;

public class InputUtils {

    public static final BufferedReader INPUT_READER = new BufferedReader(new InputStreamReader(System.in));

    public static String checkDepartmentName(BufferedReader depReader) throws IOException {
        MenuMessages.depNameInputMessage();
        String depName = depReader.readLine();
        if (InputChecks.checkIfInputEmpty(depName) && InputChecks.checkIfInputBlank(depName)) {
            return depName;
        } else {
            ErrorMessages.depNameIsEmptyOrBlank();
            return checkDepartmentName(INPUT_READER);
        }
    }

    public static String checkDepartmentId(BufferedReader depReader) throws IOException {
        MenuMessages.depIdInputMessage();
        String id = depReader.readLine();
        if (InputChecks.checkIfInputEmpty(id) && InputChecks.checkIfInputBlank(id)) {
            return id;
        } else {
            ErrorMessages.depIdIsEmptyOrBlank();
            return checkDepartmentId(INPUT_READER);
        }
    }

    public static String checkDepartmentNewName(BufferedReader depReader, String depOldName) throws IOException {
        MenuMessages.depNewNameInputMessage();
        String depNewName = depReader.readLine();
        if (InputChecks.checkIfInputEmpty(depNewName) && InputChecks.checkIfInputBlank(depNewName)) {
            return depNewName;
        } else {
            return depOldName;
        }
    }

    public static String checkEmployeeFirstName(BufferedReader empReader) throws IOException {
        MenuMessages.empFirstNameInputMessage();
        String empFirstName = empReader.readLine();
        if (InputChecks.checkIfInputEmpty(empFirstName) && InputChecks.checkIfInputBlank(empFirstName)
                && InputChecks.checkIfAlphabeticApostrophe(empFirstName)) {
            return empFirstName;
        } else if (!InputChecks.checkIfInputEmpty(empFirstName) || !InputChecks.checkIfInputBlank(empFirstName)) {
            ErrorMessages.empFirstNameIsEmptyOrBlank();
            return checkEmployeeFirstName(INPUT_READER);
        } else {
            ErrorMessages.empFirstNameIsNotValid();
            return checkEmployeeFirstName(INPUT_READER);
        }
    }

    public static String checkEmployeeLastName(BufferedReader empReader) throws IOException {
        MenuMessages.empLastNameInputMessage();
        String empLastName = empReader.readLine();
        if (InputChecks.checkIfInputEmpty(empLastName) && InputChecks.checkIfInputBlank(empLastName)
                && InputChecks.checkIfAlphabeticApostrophe(empLastName)) {
            return empLastName;
        } else if (!InputChecks.checkIfInputEmpty(empLastName) || !InputChecks.checkIfInputBlank(empLastName)) {
            ErrorMessages.empLastNameIsEmptyOrBlank();
            return checkEmployeeLastName(INPUT_READER);
        } else {
            ErrorMessages.empLastNameIsNotValid();
            return checkEmployeeLastName(INPUT_READER);
        }
    }

    public static int checkEmployeeAge(BufferedReader empReader) throws IOException {
        MenuMessages.empAgeInputMessage();
        String empAge = empReader.readLine();
        if (InputChecks.checkIfInputEmpty(empAge) && InputChecks.checkIfInputBlank(empAge)
                && InputChecks.checkIfIntegerNumber(empAge) && InputChecks.checkRange(empAge)) {
            return Integer.parseInt(empAge);
        } else if (!InputChecks.checkIfInputEmpty(empAge) || !InputChecks.checkIfInputBlank(empAge)) {
            ErrorMessages.empAgeIsEmptyOrBlank();
            return checkEmployeeAge(INPUT_READER);
        } else {
            ErrorMessages.empAgeIsNotValid();
            return checkEmployeeAge(INPUT_READER);
        }
    }

    public static String checkEmployeeEmail(BufferedReader empReader) throws IOException {
        MenuMessages.empEmailInputMessage();
        String empEmail = empReader.readLine();
        if (InputChecks.checkIfInputEmpty(empEmail) && InputChecks.checkIfInputBlank(empEmail)
                && InputChecks.checkEmailPattern(empEmail)) {
            return empEmail;
        } else if (!InputChecks.checkIfInputEmpty(empEmail) || !InputChecks.checkIfInputBlank(empEmail)) {
            ErrorMessages.empEmailIsEmptyOrBlank();
            return checkEmployeeEmail(INPUT_READER);
        } else {
            ErrorMessages.empEmailIsNotValid();
            return checkEmployeeEmail(INPUT_READER);
        }
    }

    public static BigDecimal checkEmployeeSalary(BufferedReader empReader) throws IOException {
        MenuMessages.empSalaryInputMessage();
        String empSalary = empReader.readLine();
        if (InputChecks.checkIfInputEmpty(empSalary) && InputChecks.checkIfInputBlank(empSalary)
                && InputChecks.checkIfDoubleNumber(empSalary)) {
            return BigDecimal.valueOf(Double.parseDouble(empSalary));
        } else if (!InputChecks.checkIfInputEmpty(empSalary) || !InputChecks.checkIfInputBlank(empSalary)) {
            ErrorMessages.empSalaryIsEmptyOrBlank();
            return checkEmployeeSalary(INPUT_READER);
        } else {
            ErrorMessages.empSalaryIsNotValid();
            return checkEmployeeSalary(INPUT_READER);
        }
    }

    public static int checkDepartmentsNumber(BufferedReader empReader) throws IOException {
        MenuMessages.empDepartmentsNumberInputMessage();
        String empDepartments = empReader.readLine();
        if (InputChecks.checkIfInputEmpty(empDepartments) && InputChecks.checkIfInputBlank(empDepartments)
                && InputChecks.checkIfPositiveIntegerNumber(empDepartments)) {
            return Integer.parseInt(empDepartments);
        } else if (!InputChecks.checkIfInputEmpty(empDepartments) || !InputChecks.checkIfInputBlank(empDepartments)) {
            ErrorMessages.empDepartmentsNumberIsEmptyOrBlank();
            return checkDepartmentsNumber(INPUT_READER);
        } else {
            ErrorMessages.empDepartmentsNumberIsNotValid();
            return checkDepartmentsNumber(INPUT_READER);
        }
    }

    public static DynamicArray<Department> addDepartmentsToNewEmployee(BufferedReader empReader,
                                                                       DepartmentServiceImpl dsi,
                                                                       int number) throws IOException {
        DynamicArray<Department> departments = new DynamicArray<>();
        for (int i = 0; i < number; i++) {
            String depId = checkDepartmentId(empReader);
            if (!departments.contains(dsi.findById(depId))) {
                departments.add(dsi.findById(depId));
            }
        }
        return departments;
    }

    public static DynamicArray<Department> addDepartmentsToEmployee(BufferedReader empReader,
                                                                    EmployeeServiceImpl esi,
                                                                    DepartmentServiceImpl dsi,
                                                                    int number,
                                                                    String empId) throws IOException {
        Employee employee = esi.findById(empId);
        DynamicArray<Department> departments = employee.getDepartments();
        for (int i = 0; i < number; i++) {
            String depId = checkDepartmentId(empReader);
            if (!departments.contains(dsi.findById(depId))) {
                departments.add(dsi.findById(depId));
            }
        }
        return departments;
    }

    public static DynamicArray<Department> removeDepartmentsFromEmployee(BufferedReader empReader,
                                                                         EmployeeServiceImpl esi,
                                                                         int number,
                                                                         String empId) throws IOException {
        DynamicArray<Department> departments;
        Employee employee = esi.findById(empId);
        for (int i = 0; i < number; i++) {
            String depId = checkDepartmentId(empReader);
            for (int j = 0; j < employee.getDepartments().size(); j++) {
                if (employee.getDepartments().get(j).getId().equals(depId)) {
                    employee.getDepartments().remove(j);
                }
            }
        }
        departments = employee.getDepartments();
        return departments;
    }

    public static String checkEmployeeNewFirstName(BufferedReader empReader, String empOldFirstName) throws IOException {
        MenuMessages.empNewFirstNameInputMessage();
        String empNewFirstName = empReader.readLine();
        if (InputChecks.checkIfInputEmpty(empNewFirstName) && InputChecks.checkIfInputBlank(empNewFirstName)) {
            if (InputChecks.checkIfAlphabeticApostrophe(empNewFirstName)) {
                return empNewFirstName;
            } else {
                ErrorMessages.empFirstNameIsNotValid();
                return checkEmployeeNewFirstName(empReader, empOldFirstName);
            }
        } else {
            return empOldFirstName;
        }
    }

    public static String checkEmployeeNewLastName(BufferedReader empReader, String empOldLastName) throws IOException {
        MenuMessages.empNewLastNameInputMessage();
        String empNewLastName = empReader.readLine();
        if (InputChecks.checkIfInputEmpty(empNewLastName) && InputChecks.checkIfInputBlank(empNewLastName)) {
            if (InputChecks.checkIfAlphabeticApostrophe(empNewLastName)) {
                return empNewLastName;
            } else {
                ErrorMessages.empLastNameIsNotValid();
                return checkEmployeeNewLastName(empReader, empOldLastName);
            }
        } else {
            return empOldLastName;
        }
    }

    public static int checkEmployeeNewAge(BufferedReader empReader, int empOldAge) throws IOException {
        MenuMessages.empNewAgeInputMessage();
        String empNewAge = empReader.readLine();
        if (InputChecks.checkIfInputEmpty(empNewAge) && InputChecks.checkIfInputBlank(empNewAge)) {
            if (InputChecks.checkIfIntegerNumber(empNewAge) && InputChecks.checkRange(empNewAge)) {
                return Integer.parseInt(empNewAge);
            } else {
                ErrorMessages.empAgeIsNotValid();
                return checkEmployeeNewAge(empReader, empOldAge);
            }
        } else {
            return empOldAge;
        }
    }

    public static String checkEmployeeNewEmail(BufferedReader empReader, String empOldEmail) throws IOException {
        MenuMessages.empNewEmailInputMessage();
        String empNewEmail = empReader.readLine();
        if (InputChecks.checkIfInputEmpty(empNewEmail) && InputChecks.checkIfInputBlank(empNewEmail)) {
            if (InputChecks.checkEmailPattern(empNewEmail)) {
                return empNewEmail;
            } else {
                ErrorMessages.empEmailIsNotValid();
                return checkEmployeeNewEmail(empReader, empOldEmail);
            }
        } else {
            return empOldEmail;
        }
    }

    public static BigDecimal checkEmployeeNewSalary(BufferedReader empReader, BigDecimal empOldSalary) throws IOException {
        MenuMessages.empNewSalaryInputMessage();
        String empNewSalary = empReader.readLine();
        if (InputChecks.checkIfInputEmpty(empNewSalary) && InputChecks.checkIfInputBlank(empNewSalary)) {
            if (InputChecks.checkIfDoubleNumber(empNewSalary)) {
                return BigDecimal.valueOf(Double.parseDouble(empNewSalary));
            } else {
                ErrorMessages.empSalaryIsNotValid();
                return checkEmployeeNewSalary(empReader, empOldSalary);
            }
        } else {
            return empOldSalary;
        }
    }

    public static DynamicArray<Department> removeOrAddDep(BufferedReader menuReader, DepartmentServiceImpl dsi,
                                                          EmployeeServiceImpl esi, String empId) throws IOException {
        MenuMessages.addOrRemoveOption();
        String option = menuReader.readLine().toUpperCase(Locale.ROOT);
        if (option.equals("R")) {
            int number = InputUtils.checkDepartmentsNumber(menuReader);
            return removeDepartmentsFromEmployee(menuReader, esi, number, empId);
        } else if (option.equals("A")) {
            int number = InputUtils.checkDepartmentsNumber(menuReader);
            return addDepartmentsToEmployee(menuReader, esi, dsi, number, empId);
        } else {
            ErrorMessages.optionError();
            return removeOrAddDep(menuReader, dsi, esi, empId);
        }
    }

    public static String checkEmployeeId(BufferedReader empReader) throws IOException {
        MenuMessages.empIdInputMessage();
        String id = empReader.readLine();
        if (InputChecks.checkIfInputEmpty(id) && InputChecks.checkIfInputBlank(id)) {
            return id;
        } else {
            ErrorMessages.empIdIsEmptyOrBlank();
            return checkEmployeeId(INPUT_READER);
        }
    }
}