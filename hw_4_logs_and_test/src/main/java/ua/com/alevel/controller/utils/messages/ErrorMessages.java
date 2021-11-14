package ua.com.alevel.controller.utils.messages;

public class ErrorMessages {

    public static void optionError() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Input error!
                ____________________________________________________
                """);
    }

    public static void depNameIsEmptyOrBlank() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Department name is empty or blank!
                ____________________________________________________
                """);
    }

    public static void depIdIsEmptyOrBlank() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Department id is empty or blank!
                ____________________________________________________
                """);
    }

    public static void empFirstNameIsEmptyOrBlank() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Employee's first name is empty or blank!
                ____________________________________________________
                """);
    }

    public static void empFirstNameIsNotValid() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Employee's first name allows only latin and
                cyrillic letters and apostrophe!
                ____________________________________________________
                """);
    }

    public static void empLastNameIsEmptyOrBlank() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Employee's last name is empty or blank!
                ____________________________________________________
                """);
    }

    public static void empLastNameIsNotValid() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Employee's last name allows only latin and
                cyrillic letters and apostrophe!
                ____________________________________________________
                """);
    }

    public static void empAgeIsEmptyOrBlank() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Employee's age is empty or blank!
                ____________________________________________________
                """);
    }

    public static void empAgeIsNotValid() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Employee's age should contain only positive
                integer value and be less than 120!
                ____________________________________________________
                """);
    }

    public static void empEmailIsEmptyOrBlank() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Employee's email is empty or blank!
                ____________________________________________________
                """);
    }

    public static void empEmailIsNotValid() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Employee's email should match the pattern!
                Pattern: [a-zA-Z0-9_.-]@[a-zA-Z0-9.-]
                ____________________________________________________
                """);
    }

    public static void empSalaryIsEmptyOrBlank() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Employee's email is empty or blank!
                ____________________________________________________
                """);
    }

    public static void empSalaryIsNotValid() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Employee's salary should contain only positive
                number!
                ____________________________________________________
                """);
    }

    public static void empDepartmentsNumberIsEmptyOrBlank() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Departments number is empty or blank!
                ____________________________________________________
                """);
    }

    public static void empDepartmentsNumberIsNotValid() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Numbers of departments should contain only
                positive integer number and 0!
                ____________________________________________________
                """);
    }

    public static void empIdIsEmptyOrBlank() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Employee id is empty or blank!
                ____________________________________________________
                """);
    }

    public static void printEmployeeListIsEmpty() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Employees list is Empty!
                ____________________________________________________
                """);
    }

    public static void printDepartmentsListIsEmpty() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Departments list is empty!
                ____________________________________________________
                """);
    }
}