package ua.com.alevel.console.menu;

import ua.com.alevel.console.log.ErrorLog;
import ua.com.alevel.console.log.Log;
import ua.com.alevel.console.log.UnifiedLog;
import ua.com.alevel.console.log.entries.Entry;
import ua.com.alevel.console.messages.ErrorMessages;
import ua.com.alevel.console.messages.Messages;
import ua.com.alevel.console.utilities.InputUtils;
import ua.com.alevel.controller.UserController;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

public class Menu {
    public static void runMenu() throws IOException, ParseException {
        Messages.returnMainMenuText();
        String menuOption = InputUtils.returnLine().toLowerCase(Locale.ROOT);

        switch (menuOption) {
            case "1" -> UserController.create();
            case "2" -> UserController.update();
            case "3" -> UserController.delete();
            case "4" -> UserController.findById();
            case "5" -> UserController.findAll();
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
            case "1" -> UserController.create();
            case "2" -> UserController.update();
            case "3" -> UserController.delete();
            case "4" -> UserController.findById();
            case "5" -> UserController.findAll();
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
        Messages.returnSubMenuText();
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
        Messages.returnLogsSubMenuText();
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

    public static void definePreviousTask() throws IOException, ParseException{
        Integer prevTaskOption = UserController.stackOfTasks.getLast();
        switch (prevTaskOption) {
            case 1 -> UserController.create();
            case 2 -> UserController.update();
            case 3 -> UserController.delete();
            case 4 -> UserController.findById();
            case 5 -> UserController.findAll();
            default -> {
                ErrorMessages.getSelectionErrorMessage();
                ErrorLog.errorLog.add(new Entry("Prev Task",
                        prevTaskOption.toString(), "Selection Error"));
                runMenu();
            }
        }
    }
}