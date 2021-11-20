package ua.com.alevel.console.messages;

import ua.com.alevel.console.controller.ApplicationController;
import mathset.structures.MathSet;

import java.util.Arrays;

public class MenuMessages {

    public static void mainMenuMessage() {
        System.out.println("""
                --------------------- Unit 5 - Collection ---------------------
                This MathSet implementation allows to use not unique items and
                null items (not in console).
                To achieve items uniqueness use one of set methods (6).
                ---------------------------------------------------------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - Create new MathSet
                2 - Base operations menu
                3 - Array operations menu
                4 - Math operations menu
                5 - Sorting operations menu
                6 - Set operations menu
                S - Show all sets
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Exit enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void constructorsMenuMessage() {
        System.out.println("""
                ---------------------- Construction menu ----------------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - Default (new MathSet())
                2 - Limited Size (new MathSet(int capacity, boolean expand)
                3 - Array Based (new MathSet(Number[] array))
                4 - Varargs of arrays (new MathSet(Number[] ... arrays)
                5 - From another MathSet (new MathSet(MathSet<Number> mathSet))
                6 - Varargs of MathSets (new MathSet(MathSet ... mathSets))
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Return back to main menu enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void baseOperationsMenuMessage() {
        System.out.println("""
                --------------------- Base Operations Menu --------------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - Add element (add(Number element))
                2 - Add elements (add(E ... elements))
                3 - Set element (set(Number element))
                4 - Get element by index (get(int index))
                5 - Clear all elements (clear())
                6 - Clear all matches from array (clear(Number[] array))
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Return back to main menu enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void arrayOperations() {
        System.out.println("""
                -------------------- Array Operations Menu --------------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - To array (toArray())
                2 - To array between indexes (toArray(int fIndex, int lIndex)
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Return back to main menu enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void mathOperationsMenuMessage() {
        System.out.println("""
                --------------------- Math Operations Menu --------------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - Minimal element (getMin())
                2 - Maximum element (getMax())
                3 - Average of elements (getAverage())
                4 - Median of elements (getMedian())
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Return back to main menu enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void sortingOperationsMenuMessage() {
        System.out.println("""
                -------------------- Sorting Operations Menu ------------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - Sort ascending (sortAsc())
                2 - Sort ascending between indexes (sortAsc(int fIndex, int lIndex)
                3 - Sort ascending from element (sortAsc(Number element)
                4 - Sort descending (sortDesc())
                5 - Sort descending between indexes (sortDesc(int fIndex, int lIndex)
                6 - Sort descending from element (sortDesc(Number element)
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Return back to main menu enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void setOperationsMenuMessage() {
        System.out.println("""
                --------------------- Set Operations Menu ---------------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - Transform to set
                2 - Single join (join(MathSet mathSet)
                3 - Multiple join (join(MathSet ... mathSet)
                4 - Single intersection (intersection(MathSet mathSet)
                5 - Multiple intersection (intersection(MathSet ... mathSet)
                6 - Cut math set from math set (cut(int fIndex, int lIndex))
                --------------------------------------------------------------
                All methods in this category transforms MathSet to set by default
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Return back to main menu enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void goBack() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Go back to submenu? (Y/n)
                ______________________________________________________________
                """);
    }

    public static void selectCapacity() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Enter capacity value:
                ______________________________________________________________
                """);
    }

    public static void arrayCapacity() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Enter array capacity value:
                ______________________________________________________________
                """);
    }

    public static void arrayCapacityInLoop(int index) {
        System.out.printf("""
                ------------------------ Selection Menu -----------------------
                Enter array capacity value for %s array:
                ______________________________________________________________
                """, index);
    }

    public static void expandable() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Expandable? (Y/n)
                ______________________________________________________________
                """);
    }

    public static void numbersOfArrays() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                How many arrays add?
                ______________________________________________________________
                """);
    }

    public static void numbersOfMathSetsAdd() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                How many math sets add?
                ______________________________________________________________
                """);
    }

    public static void constructionWarning() {
        System.out.print("""
                Math set with this constructor can be created only from existing
                math sets
                """);
    }

    public static void numbersOfMathSetsJoin() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                How many math sets join?
                ______________________________________________________________
                """);
    }

    public static void numbersOfMathSetsIntersection() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                How many math sets intersect?
                ______________________________________________________________
                """);
    }

    public static void addValue() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Enter value:
                ______________________________________________________________
                """);
    }

    public static void addValues() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Recommended to enter values with backspace for correct adding!
                Example: 1 2.2 -3.45 4 5 -1
                ---------------------------------------------------------------
                Enter values:
                ______________________________________________________________
                """);
    }

    public static void elementIndex() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Enter element index:
                ______________________________________________________________
                """);
    }

    public static void getElement(int arrayId, int index, Number number) {
        System.out.printf("""
                ------------------------ Selection Menu -----------------------
                Id   : %s
                Index: %s
                Value: %s
                ______________________________________________________________
                """, arrayId, index, number);
    }

    public static void arrayIndex(int index) {
        System.out.printf("""
                ------------------------ Selection Menu -----------------------
                Add value to %s index.
                ______________________________________________________________
                """, index);
    }

    public static void selectMathSet() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                MathSet id starts from 0 and increases for each created MathSet
                ---------------------------------------------------------------
                Enter id of MathSet:
                ______________________________________________________________
                """);
    }

    public static void getMin(Number number) {
        System.out.printf("""
                ------------------------- Result Menu ------------------------
                Min value: %s
                ______________________________________________________________
                """, number);
    }

    public static void getMax(Number number) {
        System.out.printf("""
                ------------------------- Result Menu ------------------------
                Max value: %s
                ______________________________________________________________
                """, number);
    }

    public static void getAverage(Number number) {
        System.out.printf("""
                ------------------------- Result Menu ------------------------
                Average value: %s
                ______________________________________________________________
                """, number);
    }

    public static void getMedian(Number number) {
        System.out.printf("""
                ------------------------- Result Menu ------------------------
                Median value: %s
                ______________________________________________________________
                """, number);
    }

    public static void getElementStartPoint() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Start sorting from element:
                ______________________________________________________________
                """);
    }

    public static void getFirstIndexSort() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Enter start sort point:
                ______________________________________________________________
                """);
    }

    public static void getLastIndexSort() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Enter end sort point:
                ______________________________________________________________
                """);
    }

    public static void getFirstIndexCut() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Enter start cut point:
                ______________________________________________________________
                """);
    }

    public static void getLastIndexCut() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Enter end cut point:
                ______________________________________________________________
                """);
    }

    public static void getFirstIndexArray() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Enter start index point:
                ______________________________________________________________
                """);
    }

    public static void getLastIndexArray() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Enter end index point:
                ______________________________________________________________
                """);
    }

    public static void cutResult(MathSet<Number> numbers) {
        System.out.printf("""
                ------------------------ Result Menu -------------------------
                Math Set has been cut:
                %s
                ______________________________________________________________
                """, numbers);
    }

    public static void toArrayResult(MathSet<Number> numbers) {
        System.out.printf("""
                ------------------------ Result Menu -------------------------
                Math Set has been converted to array:
                %s
                ______________________________________________________________
                """, Arrays.toString(numbers.toArray()));
    }

    public static void toArrayBetweenIndexesResult(MathSet<Number> numbers, int fIndex, int lIndex) {
        System.out.printf("""
                ------------------------ Result Menu -------------------------
                Math Set has been converted to array:
                %s
                ______________________________________________________________
                """, Arrays.toString(numbers.toArray(fIndex, lIndex)));
    }

    public static void mathSetUnlimitedSize(MathSet<Number> mathSet, MathSet<MathSet<Number>> mathSets) {
        System.out.printf("""
                ------------------------ Result Menu -------------------------
                MathSet created or changed:
                Id      : %s
                Elements: %s
                Size    : %s
                ______________________________________________________________
                """, mathSets.indexOf(mathSet), mathSet, mathSet.size());
    }

    public static void mathSetLimitedSize(MathSet<Number> mathSet, MathSet<MathSet<Number>> mathSets, boolean expandable) {
        if (expandable) {
            mathSetUnlimitedSize(mathSet, mathSets);
        } else {
            System.out.printf("""
                    ------------------------ Result Menu -------------------------
                    MathSet created or changed:
                    Id      : %s
                    Elements: %s
                    Size    : %s
                    Capacity: %s
                    ______________________________________________________________
                    """, mathSets.indexOf(mathSet), mathSet, mathSet.size(), mathSet.getCapacity());
        }
    }

    public static void returnAllMathSets() {
        StringBuilder mathSetsBuilder = new StringBuilder();
        mathSetsBuilder.append("id").append(" | ")
                .append("Size").append(" |  ")
                .append("Sum").append("  |  ")
                .append("Min").append("  |  ")
                .append("Max").append("  |  ")
                .append(" Average ").append("  |  ")
                .append(" Median ").append("  |  ")
                .append("Math Sets")
                .append("\n");
        for (int i = 0; i < ApplicationController.mathSets.size(); i++) {
            mathSetsBuilder.append(i).append("  | ")
                    .append(String.format("%4s", ApplicationController.mathSets.get(i).size())).append(" | ")
                    .append(String.format("%5s", ApplicationController.mathSets.get(i).getSum())).append(" | ")
                    .append(String.format("%5s", ApplicationController.mathSets.get(i).getMin())).append(" | ")
                    .append(String.format("%5s", ApplicationController.mathSets.get(i).getMax())).append(" | ")
                    .append(String.format("%11s", ApplicationController.mathSets.get(i).getAverage())).append(" | ")
                    .append(String.format("%10s", ApplicationController.mathSets.get(i).getMedian())).append(" | ")
                    .append(String.format("%-52s", ApplicationController.mathSets.get(i)))
                    .append("\n");
        }
        System.out.println(mathSetsBuilder);
    }
}