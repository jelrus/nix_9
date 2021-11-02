package ua.com.alevel.console.messages;

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

    public static void getEmptyIdInputErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. ID is empty!
                ____________________________________________________
                """
        );
    }

    public static void getEmptyNameInputErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Name is empty!
                ____________________________________________________
                """
        );
    }

    public static void getEmptyLastNameInputErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Last name is empty!
                ____________________________________________________
                """
        );
    }

    public static void getEmptyAgeInputErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Age is empty!
                ____________________________________________________
                """
        );
    }

    public static void getEmptyEmailInputErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Email is empty!
                ____________________________________________________
                """
        );
    }

    public static void getBlankIdInputErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Id is blank!
                ____________________________________________________
                """
        );
    }

    public static void getBlankNameInputErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Name is blank!
                ____________________________________________________
                """
        );
    }

    public static void getBlankLastNameInputErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Last name is blank!
                ____________________________________________________
                """
        );
    }

    public static void getBlankAgeInputErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Age is blank!
                ____________________________________________________
                """
        );
    }

    public static void getBlankEmailInputErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Email is blank!
                ____________________________________________________
                """
        );
    }

    public static void getNameIsNotAlphabeticApostropheErrorMessage(){
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Name contains inappropriate characters!
                ____________________________________________________
                """
        );
    }

    public static void getLastNameIsNotAlphabeticApostropheErrorMessage(){
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Last name contains inappropriate characters!
                ____________________________________________________
                """
        );
    }

    public static void getNotNumberErrorMessage(){
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Age doesn't contains number!
                ____________________________________________________
                """
        );
    }

    public static void getOutOfRangeErrorMessage(){
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Age isn't positive or greater than 120!
                ____________________________________________________
                """
        );
    }

    public static void getEmailNotValidErrorMessage(){
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Email doesn't match pattern!
                ____________________________________________________
                """
        );
    }

    public static void getUserNotFoundErrorMessage(){
        System.out.print("""
                ------------------ Error Message -------------------
                Error. User not found!
                ____________________________________________________
                """);
    }

    public static void getArrayIsEmptyErrorMessage(){
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Array is empty!
                ____________________________________________________
                """);
    }

    public static void getEmptyLogError() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Log is empty
                ____________________________________________________
                """);
    }

    public static void getEmptyErrorLogError() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Error Log is empty
                ____________________________________________________
                """);
    }

    public static void getEmptyUnifiedLogError() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Unified Log is empty
                ____________________________________________________
                """);
    }
}