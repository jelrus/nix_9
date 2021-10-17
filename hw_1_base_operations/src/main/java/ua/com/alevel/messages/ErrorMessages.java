package ua.com.alevel.messages;

public class ErrorMessages {
    public static void getInputErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Incorrect input! Try again.
                ____________________________________________________
                """
        );
    }

    public static void getSelectionErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. No tasks picked!
                ____________________________________________________
                """
        );
    }

    public static void getEmptyLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Line is empty!
                ____________________________________________________
                """);
    }

    public static void getBlankLineErrorMessage() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Line is blank!
                ____________________________________________________
                """);
    }

    public static void getSpellingError() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Enter number value!
                ____________________________________________________
                """);
    }

    public static void getNumberRangeError() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Pick number from [1;10] range
                -----------------------------------------------------
                """);
    }

    public static void getEmptyLogError() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Log is empty
                """);
    }

    public static void getEmptyErrorLogError() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Error Log is empty
                """);
    }

    public static void getEmptyUnifiedLogError() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Unified Log is empty
                """);
    }
}
