package ua.com.alevel.console.messages;

import ua.com.alevel.levels.first.horse.ChessField;
import ua.com.alevel.levels.first.horse.HorseMove;

import java.util.ArrayList;

public class MenuMessages {

    public static void returnMainMenuText() {
        System.out.print(""" 
                          --------------------- Module 1 ---------------------
                          Choose option by entering following numbers
                          Level 1:
                          1 - Count quantity of unique digits
                          2 - Chessboard horse with infinite moves
                          3 - Calculate square of triangle
                          
                          Level 2:
                          4 - String validator based on open/closed brackets
                          5 - Max depth of binary tree
                          
                          Level 3:
                          6 - Conway's game of life with preset points
                          7 - Conway's game of life with preset figures
                          8 - Conway's game of life with random points
                          
                          ------------------ Confirm action ------------------
                          Choose option by entering 1-8 into command line
                          Exit enter 'e'.
                          ____________________________________________________
                           """);
    }

    public static void returnSubMenuText() {
        System.out.print(
                """
                        ------------------ Confirm action ------------------
                        Do you want to return back? (y/N)
                        If you want to exit enter 'e'.
                        ____________________________________________________
                        """
        );
    }

    public static void returnInputText() {
        System.out.print("""
                           --------------------Input window--------------------
                           Enter input data here:
                           ____________________________________________________
                           """);
    }

    public static void returnHorseInputText(){
        System.out.print("""
                           --------------------Input window--------------------
                           Place horse on board or do the next move:
                           *Move must be in next format: A4
                           ____________________________________________________
                           """);
    }

    public static void returnInputAXText() {
        System.out.print("""
                           --------------------Input window--------------------
                           Enter coordinate X for Point A:
                           ____________________________________________________
                           """);
    }

    public static void returnInputBXText() {
        System.out.print("""
                           --------------------Input window--------------------
                           Enter coordinate X for Point B:
                           ____________________________________________________
                           """);
    }

    public static void returnInputCXText() {
        System.out.print("""
                           --------------------Input window--------------------
                           Enter coordinate X for Point C:
                           ____________________________________________________
                           """);
    }

    public static void returnInputAYText() {
        System.out.print("""
                           --------------------Input window--------------------
                           Enter coordinate Y for Point A:
                           ____________________________________________________
                           """);
    }

    public static void returnInputBYText(){
        System.out.print("""
                           --------------------Input window--------------------
                           Enter coordinate Y for Point B:
                           ____________________________________________________
                           """);
    }

    public static void returnInputCYText() {
        System.out.print("""
                           --------------------Input window--------------------
                           Enter coordinate Y for Point C:
                           ____________________________________________________
                           """);
    }

    public static void returnInputTextForBinaryTree() {
        System.out.print("""
                           --------------------Input window--------------------
                           Enter integer values for binary tree:
                           *Example: 10 11 8 9 3 4 1 2
                           ____________________________________________________
                           """);
    }

    public static void returnGenerationInputMessage() {
        System.out.print("""
                         --------------------Input window--------------------
                         Enter turns value:
                         ____________________________________________________
                         """);
    }

    public static void returnHeightInputMessage() {
        System.out.print("""
                         --------------------Input window--------------------
                         Enter height value:
                         ____________________________________________________
                         """);
    }

    public static void returnWidthInputMessage() {
        System.out.print("""
                         --------------------Input window--------------------
                         Enter width value:
                         ____________________________________________________
                         """);
    }

    public static void returnHorizontalPosMessage() {
        System.out.print("""
                         --------------------Input window--------------------
                         Enter horizontal position:
                         ____________________________________________________
                         """);
    }

    public static void returnVerticalPosMessage() {
        System.out.print("""
                         Enter vertical position:
                         ____________________________________________________
                         """);
    }

    public static void setPointMessage() {
        System.out.print("""
                         How many points you want to add?
                         *For skip enter '0'
                         ____________________________________________________
                         """);
    }

    public static void removePointMessage() {
        System.out.print("""
                         How many points you want to remove?
                         *For skip enter '0'
                         ____________________________________________________
                         """);
    }

    public static void setFigureMessage() {
        System.out.print("""
                         How many figures you want to add?
                         *For skip enter '0'
                         ____________________________________________________
                         """);
    }

    public static void chooseFigureTextMenu() {
        System.out.println("""
                Choose form of life:
                -----------------------------------------------------------------
                Still lives    | Oscillators         | Spaceships
                ---------------+---------------------+---------------------------
                1. Block       |6. Blinker           |10. Glider
                2. Bee Hive    |7. Toad              |11. Light-weight spaceship
                3. Loaf        |8. Beacon            |12. Medium-weight spaceship
                4. Boat        |9. Penta-decathlon   |13. Heavy-weight spaceship
                5. Tub         |                     |
                _________________________________________________________________
                """);
    }

    public static void choosePositionTextMenu() {
        System.out.println("""
                Choose position:
                ---------------------------------------------------------
                LU - Left Up     | CU - Center Up     | RU - Right Up
                -----------------+--------------------+------------------
                LM - Left Middle | CM - Center Middle | RM - Right Middle
                -----------------+--------------------+------------------
                LD - Left Down   | CD - Center Down   | RD - Right Down
                _________________________________________________________
                """);
    }

    public static void showAvailableMoves(ChessField chessField) {
        System.out.println(HorseMove.viewAvailableMoves(chessField));
        System.out.println();
    }

    public static void listOfAvailableMoves() {
        System.out.println("Try these moves: " + HorseMove.trueMoves);
        System.out.println("____________________________________________________");
    }

    public static void returnResult(String input, String output) {
        System.out.printf(
                """
                        -----------------------Result-----------------------
                        Input:  %s
                        Output: %s
                        ____________________________________________________
                        """, input, output
        );
    }

    public static void resultHeader() {
        System.out.println("-----------------------Result-----------------------");
    }

    public static void moveResult(String input, String output) {
        System.out.printf(
                """
                        Current position:    %s
                        Available positions: %s
                        *To make next move enter 'n'
                        **Current position marked on board as HP
                        ***Initial position in available moves stands for
                        turn skip
                        ____________________________________________________
                        """, input, output
        );
    }

    public static void returnSquareResult(String ax, String ay, String bx, String by, String cx, String cy,
                                          String square) {
        System.out.printf("""
                          -----------------------Result-----------------------
                          Point A[%s;%s]
                          Point B[%s;%s]
                          Point C[%s;%s]
                          ---------------------------------------------------
                          Square = %s units\u00B2
                          ____________________________________________________
                          """, ax, ay, bx, by, cx, cy, square);
    }

    public static void returnEqualityResultTrue(String inputLine, String equals) {
        System.out.printf("""
                          -----------------------Result-----------------------
                          Input:  %s
                          Result: %s
                                  Brackets are equal or there is no brackets
                          ____________________________________________________
                          """, inputLine, equals);
    }

    public static void returnEqualityResultFalse(String inputLine, String equals) {
        System.out.printf("""
                          -----------------------Result-----------------------
                          Input:  %s
                          Result: %s
                                  Brackets are not equal
                          ____________________________________________________
                          """, inputLine, equals);
    }

    public static void returnBinaryTreeMaxDepthResult(String inputLine, ArrayList<Integer> integers, String tree,
                                                      String maxDepth) {
        System.out.printf("""
                          -----------------------Result-----------------------
                          Input:        %s
                          Parsed input: %s
                          Result:
                          %s
                                  Max depth = %s nodes
                          ____________________________________________________
                          """, inputLine, integers, tree, maxDepth);
    }

    public static void returnBaseGameGrid(String grid) {
        System.out.printf("""
                                   Generation base.
                         %s
                         """ , grid);
    }

    public static void returnNextGensGameGrid(String generation, String grid) {
        System.out.printf("""
                                    Generation %s.
                         %s
                         """
                ,generation, grid);
    }

    public static void returnBaseGrid(String board) {
        System.out.printf("""
                          Board generated
                          %s
                          """, board);
    }

    public static void pointAdded(String newBoard) {
        System.out.printf("""
                          Board updated: point has been added!
                          %s
                          """, newBoard);
    }

    public static void pointRemoved(String newBoard) {
        System.out.printf("""
                          Board updated: point has been removed!
                          %s
                          """, newBoard);
    }
}
