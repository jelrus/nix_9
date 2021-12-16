package ua.com.alevel.controller.utils.messages;

import ua.com.alevel.dto.department.DepartmentResponseDTO;
import ua.com.alevel.dto.departmentemployee.DepartmentEmployeeResponseDTO;
import ua.com.alevel.dto.employee.EmployeeResponseDTO;
import ua.com.alevel.facade.DepartmentEmployeeFacade;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.facade.EmployeeFacade;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MenuMessages {

    public static void mainMenuText() {
        System.out.println("""
                ------- Main menu -------
                1. Department menu
                2. Employee menu
                3. Department and Employee menu
                -------------------------
                e - exit program
                """);
    }

    public static void departmentMenuText() {
        System.out.println("""
                ---- Department menu ----
                1. Create department
                2. Update department
                3. Delete department
                4. Find department by id
                5. Find all departments
                -------------------------
                e - to main menu
                """);
    }

    public static void employeeMenuText() {
        System.out.println("""
                ----- Employee menu -----
                1. Create employee
                2. Update employee
                3. Delete employee
                4. Find employee by id
                5. Find all employees
                -------------------------
                e - to main menu
                """);
    }

    public static void departmentEmployeeMenuText() {
        System.out.println("""
                ---- Department menu ----
                1. Assign employee to department
                2. Update relations
                3. Fire employee from department
                4. Find relations
                5. Find all relations
                -------------------------
                e - to main menu
                """);
    }

    public static void optionInput() {
        System.out.println("""
                --------------------Input window--------------------
                Choose option:
                ____________________________________________________
                """);
    }

    public static void returnBackOption() {
        System.out.println("""
                --------------------Input window--------------------
                Return back to main menu? (Y/n)
                ____________________________________________________
                """);
    }

    public static void depNameInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter department name:
                ____________________________________________________
                """);
    }

    public static void depIdInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter department id:
                ____________________________________________________
                """);
    }

    public static void depNewNameInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter department new name:
                ____________________________________________________
                *Old name won't be changed if input will be empty or
                blank.
                """);
    }

    public static void printDepartment(DepartmentResponseDTO department) {
        System.out.printf("""
                ---------------------Department---------------------
                %s
                ____________________________________________________
                """, department.toString());
    }

    public static String printAllDepartments(DepartmentFacade departmentFacade)
           throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                  IllegalAccessException {
        System.out.println("---------------------- All departments ----------------------");
        StringBuilder depBuilder = new StringBuilder();
        for (DepartmentResponseDTO deps: departmentFacade.findAll()) {
            depBuilder.append(deps).append("\n");
        }
        System.out.println(depBuilder);
        return depBuilder.toString();
    }

    public static void empFirstNameInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter employee's first name:
                ____________________________________________________
                """);
    }

    public static void empLastNameInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter employee's last name:
                ____________________________________________________
                """);
    }

    public static void empAgeInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter employee's age:
                ____________________________________________________
                """);
    }

    public static void empEmailInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter employee's email:
                *email's pattern ^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+$
                ____________________________________________________
                """);
    }

    public static void empSalaryInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter employee's salary:
                ____________________________________________________
                """);
    }

    public static void empDepartmentsNumberInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                If departments number equals 0 no departments will
                be assigned!
                ----------------------------------------------------
                Enter numbers of departments to assign/reassign:
                ____________________________________________________
                """);
    }

    public static void empNewFirstNameInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Old fields won't be changed if input will be empty or
                blank.
                ----------------------------------------------------
                Enter employee's new first name:
                ____________________________________________________
                """);
    }

    public static void empNewLastNameInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter employee's new last name:
                ____________________________________________________
                """);
    }

    public static void empNewAgeInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter employee's new age:
                ____________________________________________________
                """);
    }

    public static void empNewEmailInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter employee's new email:
                ____________________________________________________
                """);
    }

    public static void empNewSalaryInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter employee's new salary:
                ____________________________________________________
                """);
    }

    public static void empIdInputMessage() {
        System.out.println("""
                --------------------Input window--------------------
                Enter employee's id:
                ____________________________________________________
                """);
    }

    public static void addOrRemoveOption() {
        System.out.println("""
                --------------------Input window--------------------
                Add or remove departments? (A/r)
                ____________________________________________________
                """);
    }

    public static void printEmployee(EmployeeResponseDTO employeeResponseDTO) {
        System.out.printf("""
                ----------------------Employee----------------------
                %s
                ____________________________________________________
                """, employeeResponseDTO.toString());
    }

    public static void printAllEmployees(EmployeeFacade employeeFacade)
           throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                  IllegalAccessException {
        System.out.println("---------------------- All employees ----------------------");
        StringBuilder empBuilder = new StringBuilder();
        for (EmployeeResponseDTO emps: employeeFacade.findAll()) {
            empBuilder.append(emps).append("\n");
        }
        System.out.println(empBuilder);
    }

    public static void relationId() {
        System.out.println("""
                --------------------Input window--------------------
                Enter relation's id:
                ____________________________________________________
                """);
    }

    public static void printRelation(DepartmentEmployeeResponseDTO departmentEmployeeResponseDTO) {
        System.out.printf("""
                --------------------- Relation ---------------------
                %s
                ____________________________________________________
                """, departmentEmployeeResponseDTO.toString());
    }

    public static void printAllRelations(DepartmentEmployeeFacade departmentEmployeeFacade)
           throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                  IllegalAccessException {
        StringBuilder depEmpBuilder = new StringBuilder();
        System.out.println("---------------------- All relations ----------------------");
        for (DepartmentEmployeeResponseDTO depEmps: departmentEmployeeFacade.findAll()) {
            depEmpBuilder.append(depEmps).append("\n");
        }
        System.out.println(depEmpBuilder);
    }
}