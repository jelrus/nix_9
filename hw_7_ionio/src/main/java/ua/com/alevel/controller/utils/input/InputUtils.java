package ua.com.alevel.controller.utils.input;

import ua.com.alevel.controller.utils.messages.ErrorMessages;
import ua.com.alevel.controller.utils.messages.MenuMessages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

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

    public static String checkRelationId(BufferedReader relReader) throws IOException {
        MenuMessages.relationId();
        String id = relReader.readLine();
        if (InputChecks.checkIfInputEmpty(id) && InputChecks.checkIfInputBlank(id)) {
            return id;
        } else {
            ErrorMessages.depIdIsEmptyOrBlank();
            return checkDepartmentId(INPUT_READER);
        }
    }
}