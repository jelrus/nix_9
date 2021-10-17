package ua.com.alevel.menu;

import ua.com.alevel.log.ErrorLog;
import ua.com.alevel.log.Log;
import ua.com.alevel.log.UnifiedLog;
import ua.com.alevel.log.entries.Entry;
import ua.com.alevel.messages.ErrorMessages;
import ua.com.alevel.messages.MenuMessages;
import ua.com.alevel.tasks.Tasks;
import ua.com.alevel.utilities.InputUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

public class Menu {
    public static void runMenu() throws IOException, ParseException {
        MenuMessages.returnMainMenuText();
        String menuOption = InputUtils.returnLine().toLowerCase(Locale.ROOT);

        switch (menuOption) {
            case "1" -> Tasks.sumOfDigitsInLine();
            case "2" -> Tasks.filterAndCountChars();
            case "3" -> Tasks.endLesson();
            case "l" -> Log.showLog();
            case "er" -> ErrorLog.showErrorLog();
            case "ul" -> UnifiedLog.showUnifiedLog();
            case "e" -> System.exit(0);
            default -> {
                ErrorMessages.getInputErrorMessage();
                ErrorLog.errorLog.add(new Entry("Menu", menuOption, "Input Error"));
                runMenuShortened();
            }
        }
    }

    public static void runMenuShortened() throws IOException, ParseException {
        String menuOption = InputUtils.returnLine().toLowerCase(Locale.ROOT);
        switch (menuOption) {
            case "1" -> Tasks.sumOfDigitsInLine();
            case "2" -> Tasks.filterAndCountChars();
            case "3" -> Tasks.endLesson();
            case "l" -> Log.showLog();
            case "er" -> ErrorLog.showErrorLog();
            case "ul" -> UnifiedLog.showUnifiedLog();
            case "e" -> System.exit(0);
            default -> {
                ErrorMessages.getInputErrorMessage();
                ErrorLog.errorLog.add(new Entry("Menu", menuOption, "Input Error"));
                runMenuShortened();
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
                ErrorLog.errorLog.add(new Entry("Submenu", subMenuOption, "Input Error"));
                runSubMenu();
            }
        }
    }

    public static void runLogsSubMenu() throws IOException, ParseException {
        MenuMessages.returnLogsSubMenuText();
        String logsSubMenuOption = InputUtils.returnLine().toLowerCase(Locale.ROOT);
        switch (logsSubMenuOption) {
            case "y" -> runMenu();
            case "e" -> System.exit(0);
            default -> {
                ErrorMessages.getInputErrorMessage();
                ErrorLog.errorLog.add(new Entry("Logs Submenu", logsSubMenuOption, "Input Error"));
                Menu.runLogsSubMenu();
            }
        }
    }

    public static void definePreviousTask() throws IOException, ParseException {
        Integer prevTaskOption = Tasks.stackOfTasks.getLast();
        switch (prevTaskOption) {
            case 1 -> Tasks.sumOfDigitsInLine();
            case 2 -> Tasks.filterAndCountChars();
            case 3 -> Tasks.endLesson();
            default -> {
                ErrorMessages.getSelectionErrorMessage();
                ErrorLog.errorLog.add(new Entry("Prev Task",
                        prevTaskOption.toString(), "Selection Error"));
                runMenu();
            }
        }
    }
}