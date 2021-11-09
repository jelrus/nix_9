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

    public static void getEmptyLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Line is empty!
                ____________________________________________________
                """);
    }

    public static void getBlankLineErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Line is blank!
                ____________________________________________________
                """);
    }

    public static void getNotInRangeErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. String length must equals 2!
                To see available moves input must contain:
                Letter as the first symbol;
                Digit as the second symbol;
                Example: A1.
                ____________________________________________________
                """);
    }

    public static void getNotAvailableMoveErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. This move is not available!
                           """);
    }

    public static void getIncorrectPositionOrNoMoves() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Incorrect position entered or moves list is
                empty!
                ____________________________________________________
                           """);
    }

    public static void getNotANumberErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Some input lines doesn't contain numbers!
                ____________________________________________________
                           """);
    }

    public static void getNotPositiveNumberErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. There is no positive integer in input line!
                ____________________________________________________
                           """);
    }

    public static void getNotExistErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. This point doesn't exist!
                ____________________________________________________
                           """);
    }

    public static void getPointIsOutOfBoundsErrorMessage() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Point is out of bounds!
                ____________________________________________________
                           """);
    }

    public static void getFigureOutOfBoundsErrorMessage() {
        System.out.print("""
                        ------------------------------------ Error Message ----------------------------------
                        Error. Figure is out of bounds or doesn't fit!
                        VxH - size of figure.
                        RUP[V;H] - Right Upper Corner[Vertical;Horizontal].
                        -------------------------------------------------------------------------------------
                        Still lives         | Oscillators                | Spaceships
                        --------------------+----------------------------+-----------------------------------
                        Block     2x2 [0,0] |Blinker         3x3   [1,0] |Glider                    3x4 [0,0]
                        Bee Hive  3x4 [0,0] |Toad            4x4   [1,0] |Light-weight spaceship    5x6 [0,0]
                        Loaf      4x4 [0,0] |Beacon          4x4   [0,0] |Medium-weight spaceship   6x6 [0,0]
                        Boat      3x3 [0,0] |Penta-decathlon 10x20 [0,0] |Heavy-weight spaceship    7x6 [0,0]
                        Tub       3x3 [0,0] |                            |
                        ______________________________________________________________________________________
                        """);
    }
}
