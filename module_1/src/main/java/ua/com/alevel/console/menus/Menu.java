package ua.com.alevel.console.menus;

import ua.com.alevel.console.messages.ErrorMessages;
import ua.com.alevel.console.messages.MenuMessages;
import ua.com.alevel.console.utils.InputUtils;
import ua.com.alevel.console.utils.TasksUtils;
import ua.com.alevel.levels.first.horse.HorseMove;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

public class Menu {

    public static void runMenu() throws IOException, ParseException {
        MenuMessages.returnMainMenuText();
        String menuOption = InputUtils.returnLine().toLowerCase(Locale.ROOT);
        switch (menuOption) {
            case "1" -> TasksUtils.countDigits();
            case "2" -> {
                HorseMove.trueMoves.clear();
                TasksUtils.startHorseMoving();
            }
            case "3" -> TasksUtils.calculateTriangleSquare();
            case "4" -> TasksUtils.bracketsEquality();
            case "5" -> TasksUtils.maxDepthOfBinaryTree();
            case "6" -> TasksUtils.gameOfLife();
            case "7" -> TasksUtils.gameOfLifeWithPresetFigures();
            case "8" -> TasksUtils.gameOfLifeWithRandomPoints();
            case "e" -> System.exit(0);
            default -> {
                ErrorMessages.getInputErrorMessage();
                runMenu();
            }
        }
    }

    public static void runSubMenu() throws IOException, ParseException {
        MenuMessages.returnSubMenuText();
        String subMenuOption = InputUtils.returnLine().toLowerCase(Locale.ROOT);
        switch (subMenuOption) {
            case "y" -> runMenu();
            case "n" -> definePreviousTask();
            case "e" -> System.exit(0);
            default -> {
                ErrorMessages.getInputErrorMessage();
                runSubMenu();
            }
        }
    }

    public static void definePreviousTask() throws IOException, ParseException {
        Integer prevTaskOption = TasksUtils.stackOfTasks.getLast();
        switch (prevTaskOption) {
            case 1 -> TasksUtils.countDigits();
            case 2 -> TasksUtils.startHorseMoving();
            case 3 -> TasksUtils.calculateTriangleSquare();
            case 4 -> TasksUtils.bracketsEquality();
            case 5 -> TasksUtils.maxDepthOfBinaryTree();
            case 6 -> TasksUtils.gameOfLife();
            case 7 -> TasksUtils.gameOfLifeWithPresetFigures();
            case 8 -> TasksUtils.gameOfLifeWithRandomPoints();
            default -> {
                ErrorMessages.getSelectionErrorMessage();
                runMenu();
            }
        }
    }
}
