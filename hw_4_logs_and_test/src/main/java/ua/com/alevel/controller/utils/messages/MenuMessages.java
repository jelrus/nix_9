package ua.com.alevel.controller.utils.messages;

import ua.com.alevel.entity.implementation.Department;
import ua.com.alevel.entity.implementation.Employee;
import ua.com.alevel.service.implementation.DepartmentServiceImpl;
import ua.com.alevel.service.implementation.EmployeeServiceImpl;

public class MenuMessages {

    public static void mainMenuText() {
        System.out.print("""
                ------- Main menu -------
                1. Department menu
                2. Employee menu
                -------------------------
                e - exit program
                """);
    }

    public static void departmentMenuText() {
        System.out.print("""
                ---- Department menu ----
                1. Create department
                2. Update department
                3. Delete department
                4. Find department by id
                5. Find all departments
                -------------------------
                """);
    }

    public static void optionInput() {
        System.out.print("""
                --------------------Input window--------------------
                Choose option:
                ____________________________________________________
                """);
    }

    public static void returnBackOption() {
        System.out.print("""
                --------------------Input window--------------------
                Return back to main menu? (Y/n)
                ____________________________________________________
                """);
    }

    public static void depNameInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter department name:
                ____________________________________________________
                """);
    }

    public static void depIdInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter department id:
                ____________________________________________________
                """);
    }

    public static void depNewNameInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter department new name:
                ____________________________________________________
                *Old name won't be changed if input will be empty or
                blank.
                """);
    }

    public static void printDepartment(Department department) {
        System.out.printf("""
                ---------------------Department---------------------
                %s
                ____________________________________________________
                """, department.toString());
    }

    public static void printAllDepartments(DepartmentServiceImpl departmentServiceImpl) {
        StringBuilder departmentServiceBuilder = new StringBuilder();
        departmentServiceBuilder.append("----------------------------- ")
                .append("DEPARTMENTS")
                .append(" -----------------------------")
                .append("\n")
                .append("---------------- ID -----------------")
                .append("+------------ NAME --------------+")
                .append("\n");
        for (int i = 0; i < departmentServiceImpl.findAll().size(); i++) {
            departmentServiceBuilder
                    .append(String.format("%-36s", departmentServiceImpl.findAll().get(i).getId()))
                    .append(" | ")
                    .append(String.format("%-30s", departmentServiceImpl.findAll().get(i).getName()))
                    .append(" |")
                    .append("\n");
        }
        departmentServiceBuilder.delete(departmentServiceBuilder.length() - 1, departmentServiceBuilder.length());
        departmentServiceBuilder.append("\n")
                .append("-------------------------------------+--------------------------------+");
        System.out.println(departmentServiceBuilder);
    }

    public static void employeeMenuText() {
        System.out.print("""
                ----- Employee menu -----
                1. Create employee
                2. Update employee
                3. Delete employee
                4. Find employee by id
                5. Find all employees
                -------------------------
                """);
    }

    public static void empFirstNameInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter employee's first name:
                ____________________________________________________
                """);
    }

    public static void empLastNameInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter employee's last name:
                ____________________________________________________
                """);
    }

    public static void empAgeInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter employee's age:
                ____________________________________________________
                """);
    }

    public static void empEmailInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter employee's email:
                ____________________________________________________
                """);
    }

    public static void empSalaryInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter employee's salary:
                ____________________________________________________
                """);
    }

    public static void empDepartmentsNumberInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                If departments number equals 0 no departments will
                be assigned!
                ----------------------------------------------------
                Enter numbers of departments to assign/reassign:
                ____________________________________________________
                """);
    }

    public static void empNewFirstNameInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Old fields won't be changed if input will be empty or
                blank.
                ----------------------------------------------------
                Enter employee's new first name:
                ____________________________________________________
                """);
    }

    public static void empNewLastNameInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter employee's new last name:
                ____________________________________________________
                """);
    }

    public static void empNewAgeInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter employee's new age:
                ____________________________________________________
                """);
    }

    public static void empNewEmailInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter employee's new email:
                ____________________________________________________
                """);
    }

    public static void empNewSalaryInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter employee's new salary:
                ____________________________________________________
                """);
    }

    public static void empIdInputMessage() {
        System.out.print("""
                --------------------Input window--------------------
                Enter employee's id:
                ____________________________________________________
                """);
    }

    public static void addOrRemoveOption() {
        System.out.print("""
                --------------------Input window--------------------
                Add or remove departments? (A/r)
                ____________________________________________________
                """);
    }

    public static void printEmployee(Employee employee) {
        System.out.printf("""
                ----------------------Employee----------------------
                %s
                ____________________________________________________
                """, employee.toString());
    }

    public static void printAllEmployees(EmployeeServiceImpl employeeServiceImpl) {
        StringBuilder employeeServiceBuilder = new StringBuilder();
        employeeServiceBuilder.append("--------------------------------------------------------------------------")
                .append("------------------ ")
                .append("EMPLOYEES")
                .append(" -----------------------------------------------------------------------")
                .append("-------------------")
                .append("\n")
                .append("---------------- ID -----------------+")
                .append("--------- FIRST NAME -----------")
                .append("+--------- LAST NAME -----------+")
                .append("-- AGE --")
                .append("+------------ EMAIL -----------+")
                .append("-- SALARY --")
                .append("+----------- DEPARTMENTS -----------+")
                .append("\n");
        for (int i = 0; i < employeeServiceImpl.findAll().size(); i++) {
            employeeServiceBuilder
                    .append(String.format("%-36s", employeeServiceImpl.findAll().get(i).getId()))
                    .append(" | ")
                    .append(String.format("%-30s", employeeServiceImpl.findAll().get(i).getFirstName()))
                    .append(" |")
                    .append(String.format("%-30s", employeeServiceImpl.findAll().get(i).getLastName()))
                    .append(" |")
                    .append(String.format("%-8s", employeeServiceImpl.findAll().get(i).getAge()))
                    .append(" |")
                    .append(String.format("%-29s", employeeServiceImpl.findAll().get(i).getEmail()))
                    .append(" |")
                    .append(String.format("%-11s", employeeServiceImpl.findAll().get(i).getSalary()))
                    .append(" |");
            if (employeeServiceImpl.findAll().get(i).getDepartments().size() != 0) {
                for (int j = 0; j < employeeServiceImpl.findAll().get(i).getDepartments().size(); j++) {
                    if (j == 0) {
                        employeeServiceBuilder.
                                append(String.format("%-36s", employeeServiceImpl.findAll()
                                        .get(i).getDepartments().get(j).getId()))
                                .append(" |")
                                .append("\n");
                    } else {
                        employeeServiceBuilder.append(String.format("%-36s", " "))
                                .append(" | ")
                                .append(String.format("%-30s", " "))
                                .append(" |")
                                .append(String.format("%-30s", " "))
                                .append(" |")
                                .append(String.format("%-8s", " "))
                                .append(" |")
                                .append(String.format("%-29s", " "))
                                .append(" |")
                                .append(String.format("%-11s", " "))
                                .append(" |")
                                .append(String.format("%-36s", employeeServiceImpl.findAll()
                                        .get(i).getDepartments().get(j).getId()))
                                .append(" |")
                                .append("\n");
                    }
                }
            } else {
                employeeServiceBuilder.append("\n");
            }
        }
        employeeServiceBuilder.append("\n");
        System.out.println(employeeServiceBuilder);
    }
}