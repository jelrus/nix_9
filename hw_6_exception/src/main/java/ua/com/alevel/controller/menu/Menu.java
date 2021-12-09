package ua.com.alevel.controller.menu;

import ua.com.alevel.controller.messages.ErrorMessages;
import ua.com.alevel.controller.messages.MenuMessages;
import ua.com.alevel.controller.utils.CalendarHelper;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Locale;

public class Menu {

    public static final LinkedList<Integer> mainMenuTasks = new LinkedList<>();
    public static final LinkedList<Integer> subMenusTasks = new LinkedList<>();

    public static void run() throws IOException, ParseException {
        mainMenuTasks.add(0);
        MenuMessages.mainMenuMessage();
        String option = CalendarHelper.reader.readLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> runBaseMenu();
            case "2" -> runAppendingMenu();
            case "3" -> runSubtractionMenu();
            case "4" -> runDistinctionMenu();
            case "5" -> runListMenu();
            case "e" -> System.exit(0);
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        MenuMessages.goBack();
        returnToSubMenu();
    }

    public static void runBaseMenu() throws IOException, ParseException {
        mainMenuTasks.add(1);
        MenuMessages.baseMenuMessage();
        String option = CalendarHelper.reader.readLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> CalendarHelper.createOrChangeInitialCalendar();
            case "2" -> CalendarHelper.changeInputFormat();
            case "3" -> CalendarHelper.changeOutputFormat();
            case "e" -> run();
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        run();
    }

    public static void runAppendingMenu() throws IOException, ParseException {
        mainMenuTasks.add(2);
        MenuMessages.appendingMenuMessage();
        String option = CalendarHelper.reader.readLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> CalendarHelper.appendMillis();
            case "2" -> CalendarHelper.appendSeconds();
            case "3" -> CalendarHelper.appendMinutes();
            case "4" -> CalendarHelper.appendHours();
            case "5" -> CalendarHelper.appendDays();
            case "6" -> CalendarHelper.appendYears();
            case "e" -> run();
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        run();
    }

    public static void runSubtractionMenu() throws IOException, ParseException {
        mainMenuTasks.add(3);
        MenuMessages.subtractionMenuMessage();
        String option = CalendarHelper.reader.readLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> CalendarHelper.subtractMillis();
            case "2" -> CalendarHelper.subtractSeconds();
            case "3" -> CalendarHelper.subtractMinutes();
            case "4" -> CalendarHelper.subtractHours();
            case "5" -> CalendarHelper.subtractDays();
            case "6" -> CalendarHelper.subtractYears();
            case "e" -> run();
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        run();
    }

    public static void runDistinctionMenu() throws IOException, ParseException {
        mainMenuTasks.add(4);
        MenuMessages.distinctionMenuMessage();
        String option = CalendarHelper.reader.readLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> CalendarHelper.distinctMillis();
            case "2" -> CalendarHelper.distinctSeconds();
            case "3" -> CalendarHelper.distinctMinutes();
            case "4" -> CalendarHelper.distinctHours();
            case "5" -> CalendarHelper.distinctDays();
            case "6" -> CalendarHelper.distinctYears();
            case "7" -> CalendarHelper.distinct();
            case "e" -> run();
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        run();
    }

    public static void runListMenu() throws IOException, ParseException {
        mainMenuTasks.add(5);
        MenuMessages.sortingMenuMessage();
        String option = CalendarHelper.reader.readLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> CalendarHelper.createList();
            case "2" -> CalendarHelper.sortListAsc();
            case "3" -> CalendarHelper.sortListDesc();
            case "4" -> CalendarHelper.showList();
            case "e" -> run();
            default -> {
                ErrorMessages.inputErrorMessage();
                run();
            }
        }
        run();
    }

    public static void returnToSubMenu() throws IOException, ParseException {
        MenuMessages.goBack();
        String option = CalendarHelper.reader.readLine().toLowerCase(Locale.ROOT);
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
            case 1 -> runBaseMenu();
            case 2 -> runAppendingMenu();
            case 3 -> runSubtractionMenu();
            case 4 -> runDistinctionMenu();
            case 5 -> runListMenu();
            default -> {
                ErrorMessages.selectionErrorMessage();
                run();
            }
        }
    }

    public static void definePreviousTask() throws IOException, ParseException {
        Integer prevTaskOption = subMenusTasks.getLast();
        switch (prevTaskOption) {
            case 0 -> run();
            case 1 -> CalendarHelper.createOrChangeInitialCalendar();
            case 2 -> CalendarHelper.changeInputFormat();
            case 3 -> CalendarHelper.changeOutputFormat();
            case 4 -> CalendarHelper.appendMillis();
            case 5 -> CalendarHelper.appendSeconds();
            case 6 -> CalendarHelper.appendMinutes();
            case 7 -> CalendarHelper.appendHours();
            case 8 -> CalendarHelper.appendDays();
            case 9 -> CalendarHelper.appendYears();
            case 10 -> CalendarHelper.subtractMillis();
            case 11 -> CalendarHelper.subtractSeconds();
            case 12 -> CalendarHelper.subtractMinutes();
            case 13 -> CalendarHelper.subtractHours();
            case 14 -> CalendarHelper.subtractDays();
            case 15 -> CalendarHelper.subtractYears();
            case 16 -> CalendarHelper.distinctMillis();
            case 17 -> CalendarHelper.distinctSeconds();
            case 18 -> CalendarHelper.distinctMinutes();
            case 19 -> CalendarHelper.distinctHours();
            case 20 -> CalendarHelper.distinctDays();
            case 21 -> CalendarHelper.distinctYears();
            case 22 -> CalendarHelper.distinct();
            case 23 -> CalendarHelper.createList();
            case 24 -> CalendarHelper.sortListAsc();
            case 25 -> CalendarHelper.sortListDesc();
            case 26 -> CalendarHelper.showList();
            default -> {
                ErrorMessages.selectionErrorMessage();
                run();
            }
        }
    }
}