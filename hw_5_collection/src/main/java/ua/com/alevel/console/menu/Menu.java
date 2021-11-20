package ua.com.alevel.console.menu;

import ua.com.alevel.console.controller.ApplicationController;
import ua.com.alevel.console.messages.ErrorMessages;
import ua.com.alevel.console.messages.MenuMessages;
import ua.com.alevel.console.utils.InputUtils;
import mathset.structures.MathSet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

public class Menu {

    public static final MathSet<Integer> mainMenuTasks = new MathSet<>();
    public static final MathSet<Integer> subMenusTasks = new MathSet<>();

    public static void run() throws IOException, ParseException {
        mainMenuTasks.add(0);
        MenuMessages.mainMenuMessage();
        String option = InputUtils.returnLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> runConstructorsMenu();
            case "2" -> runBaseOperationsMenu();
            case "3" -> runArrayOperationsMenu();
            case "4" -> runMathOperationsMenu();
            case "5" -> runSortingOperationsMenu();
            case "6" -> runSetOperationsMenu();
            case "s" -> {
                subMenusTasks.add(-1);
                MenuMessages.returnAllMathSets();
            }
            case "e" -> System.exit(0);
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void runConstructorsMenu() throws IOException, ParseException {
        mainMenuTasks.add(1);
        MenuMessages.constructorsMenuMessage();
        String option = InputUtils.returnLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> ApplicationController.createDefaultMathSet();
            case "2" -> ApplicationController.createLimitedSizeMathSet();
            case "3" -> ApplicationController.createArrayBasedMathSet();
            case "4" -> ApplicationController.createVarArgsArraysBasedMathSet();
            case "5" -> ApplicationController.createMathSetBasedOnMathSet();
            case "6" -> ApplicationController.createMathSetBasedOnVarArgsOfMathSets();
            case "e" -> run();
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        run();
    }

    public static void runBaseOperationsMenu() throws IOException, ParseException {
        mainMenuTasks.add(2);
        MenuMessages.baseOperationsMenuMessage();
        String option = InputUtils.returnLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> ApplicationController.addElement();
            case "2" -> ApplicationController.addElements();
            case "3" -> ApplicationController.setElement();
            case "4" -> ApplicationController.getElementByIndex();
            case "5" -> ApplicationController.clear();
            case "6" -> ApplicationController.clearAllFromArray();
            case "e" -> run();
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        run();
    }

    public static void runArrayOperationsMenu() throws IOException, ParseException {
        mainMenuTasks.add(3);
        MenuMessages.arrayOperations();
        String option = InputUtils.returnLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> ApplicationController.toArray();
            case "2" -> ApplicationController.toArrayBetweenIndexes();
            case "e" -> run();
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        run();
    }

    public static void runMathOperationsMenu() throws IOException, ParseException {
        mainMenuTasks.add(4);
        MenuMessages.mathOperationsMenuMessage();
        String option = InputUtils.returnLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> ApplicationController.getMin();
            case "2" -> ApplicationController.getMax();
            case "3" -> ApplicationController.getAverage();
            case "4" -> ApplicationController.getMedian();
            case "e" -> run();
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        run();
    }

    public static void runSortingOperationsMenu() throws IOException, ParseException {
        mainMenuTasks.add(5);
        MenuMessages.sortingOperationsMenuMessage();
        String option = InputUtils.returnLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> ApplicationController.sortAsc();
            case "2" -> ApplicationController.sortAscBetweenIndexes();
            case "3" -> ApplicationController.sortAscFromElement();
            case "4" -> ApplicationController.sortDesc();
            case "5" -> ApplicationController.sortDescBetweenIndexes();
            case "6" -> ApplicationController.sortDescFromElement();
            case "e" -> run();
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        run();
    }

    public static void runSetOperationsMenu() throws IOException, ParseException {
        mainMenuTasks.add(6);
        MenuMessages.setOperationsMenuMessage();
        String option = InputUtils.returnLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> ApplicationController.transformToSet();
            case "2" -> ApplicationController.joinWithSingleMathSet();
            case "3" -> ApplicationController.joinWithMultipleMathSets();
            case "4" -> ApplicationController.intersectionWithSingleMathSet();
            case "5" -> ApplicationController.intersectionWithMultipleMathSets();
            case "6" -> ApplicationController.cutElements();
            case "e" -> run();
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        run();
    }

    public static void returnToSubMenu() throws IOException, ParseException {
        String option = InputUtils.returnLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "y" -> definePreviousTaskInMainMenu();
            case "n" -> definePreviousTask();
            case "e" -> System.exit(0);
            default -> {
                ErrorMessages.selectionErrorMessage();
                run();
            }
        }
    }

    public static void definePreviousTaskInMainMenu() throws IOException, ParseException {
        Integer prevTaskOption = mainMenuTasks.getLast();
        switch (prevTaskOption) {
            case 1 -> runConstructorsMenu();
            case 2 -> runBaseOperationsMenu();
            case 3 -> runArrayOperationsMenu();
            case 4 -> runMathOperationsMenu();
            case 5 -> runSortingOperationsMenu();
            case 6 -> runSetOperationsMenu();
            default -> {
                ErrorMessages.selectionErrorMessage();
                run();
            }
        }
    }

    public static void definePreviousTask() throws IOException, ParseException {
        Integer prevTaskOption = subMenusTasks.getLast();
        switch (prevTaskOption) {
            case -1 -> {
                MenuMessages.returnAllMathSets();
                MenuMessages.goBack();
                Menu.returnToSubMenu();
            }
            case 0 -> run();
            case 1 -> ApplicationController.createDefaultMathSet();
            case 2 -> ApplicationController.createLimitedSizeMathSet();
            case 3 -> ApplicationController.createArrayBasedMathSet();
            case 4 -> ApplicationController.createVarArgsArraysBasedMathSet();
            case 5 -> ApplicationController.createMathSetBasedOnMathSet();
            case 6 -> ApplicationController.createMathSetBasedOnVarArgsOfMathSets();
            case 7 -> ApplicationController.addElement();
            case 8 -> ApplicationController.addElements();
            case 9 -> ApplicationController.setElement();
            case 10 -> ApplicationController.getElementByIndex();
            case 11 -> ApplicationController.clear();
            case 12 -> ApplicationController.clearAllFromArray();
            case 13 -> ApplicationController.toArray();
            case 14 -> ApplicationController.toArrayBetweenIndexes();
            case 15 -> ApplicationController.getMin();
            case 16 -> ApplicationController.getMax();
            case 17 -> ApplicationController.getAverage();
            case 18 -> ApplicationController.getMedian();
            case 19 -> ApplicationController.sortAsc();
            case 20 -> ApplicationController.sortAscBetweenIndexes();
            case 21 -> ApplicationController.sortAscFromElement();
            case 22 -> ApplicationController.sortDesc();
            case 23 -> ApplicationController.sortDescBetweenIndexes();
            case 24 -> ApplicationController.sortDescFromElement();
            case 25 -> ApplicationController.transformToSet();
            case 26 -> ApplicationController.joinWithSingleMathSet();
            case 27 -> ApplicationController.joinWithMultipleMathSets();
            case 28 -> ApplicationController.intersectionWithSingleMathSet();
            case 29 -> ApplicationController.intersectionWithMultipleMathSets();
            case 30 -> ApplicationController.cutElements();
            default -> {
                ErrorMessages.selectionErrorMessage();
                run();
            }
        }
    }
}