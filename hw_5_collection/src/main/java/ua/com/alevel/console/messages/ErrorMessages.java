package ua.com.alevel.console.messages;

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

    public static void noSetsCreatedErrorMessage() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. No sets were created! Create MathSet first!
                ____________________________________________________
                """);
    }

    public static void setIdNotFound() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Set wasn't created or id is incorrect!
                ____________________________________________________
                """);
    }

    public static void mathSetIsFull() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. MathSet with fixed size is full!
                       Use set or clear method to change values!
                ____________________________________________________
                """);
    }

    public static void mathSetDoesNotFit() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Second set size is greater than capacity in first!
                       Use clear method or create new math set!
                ____________________________________________________
                """);
    }

    public static void indexOutOfBounds() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Node index is out of bounds!
                ____________________________________________________
                """);
    }

    public static void notAllElementsAdded() {
        System.out.println("""
                ------------------ Error Message -------------------
                Warning. Not all elements were added!
                         Math Set have fixed size!
                ____________________________________________________
                """);
    }
}