package ua.com.alevel.controller.messages;

public class ErrorMessages {

    public static void inputErrorMessage() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Incorrect input! Try again.
                ____________________________________________________
                """);
    }

    public static void selectionErrorMessage() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Nothing was selected!
                ____________________________________________________
                """);
    }

    public static void listIsEmpty() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. List is empty!
                ____________________________________________________
                """);
    }
}