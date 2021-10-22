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

    public static void getEmptyBeginIndexLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Input Begin Index Line is empty!
                ____________________________________________________
                """);
    }

    public static void getBlankBeginIndexLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Input Begin Index Line is blank!
                ____________________________________________________
                """);
    }

    public static void getEmptyEndIndexLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Input End Index Line is empty!
                ____________________________________________________
                """);
    }

    public static void getBlankEndIndexLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Input End Index Line is blank!
                ____________________________________________________
                """);
    }

    public static void getEmptyLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Input Line is empty!
                ____________________________________________________
                """);
    }

    public static void getBlankLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Input Line is blank!
                ____________________________________________________
                """);
    }

    public static void getEmptyIndexLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Index Line is empty!
                ____________________________________________________
                """);
    }

    public static void getBlankIndexLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Index Line is blank!
                ____________________________________________________
                """);
    }

    public static void getEmptyCharLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Character Input Line is empty!
                ____________________________________________________
                """);
    }

    public static void getBlankCharLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Character Input Line is blank!
                ____________________________________________________
                """);
    }

    public static void getEmptySubsequenceLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Subsequence Line is empty!
                ____________________________________________________
                """);
    }

    public static void getBlankSubsequenceLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Subsequence Line is blank!
                ____________________________________________________
                """);
    }

    public static void getStringNotContainsSubsequenceError(){
        System.out.println("""
                ------------------- Error Message --------------------
                Error. Input String doesn't contains this subsequence!
                ______________________________________________________
                """);
    }

    public static void getStringNotContainsCharacterError(){
        System.out.println("""
                ------------------- Error Message --------------------
                Error. Input String doesn't contains this character!
                ______________________________________________________
                """);
    }

    public static void getNumberFormatError() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Enter number value!
                ____________________________________________________
                """);
    }

    public static void getCharacterFormatError(){
        System.out.println("""
                           ------------------ Error Message -------------------
                           Error. Entered value is too long for Character!
                           ____________________________________________________
                           """);
    }

    public static void getBeginIndexOutOfBoundsError() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Begin Index out of bounds!
                -----------------------------------------------------
                """);
    }

    public static void getEndIndexOutOfBoundsError() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. End Index out of bounds!
                -----------------------------------------------------
                """);
    }

    public static void getBeginIndexGreaterThanEndIndexError() {
        System.out.println("""
                ------------------ Error Message -------------------
                Error. Begin Index greater than End Index!
                -----------------------------------------------------
                """);
    }

    public static void getEmptyLogError() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Log is empty
                """);
    }

    public static void getEmptyErrorLogError() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Error Log is empty
                """);
    }

    public static void getEmptyUnifiedLogError() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Unified Log is empty
                """);
    }

}
