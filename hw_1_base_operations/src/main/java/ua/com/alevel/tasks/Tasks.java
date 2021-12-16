package ua.com.alevel.tasks;

import ua.com.alevel.log.ErrorLog;
import ua.com.alevel.log.Log;
import ua.com.alevel.log.entries.Entry;
import ua.com.alevel.controller.menu.Menu;
import ua.com.alevel.messages.ErrorMessages;
import ua.com.alevel.messages.MenuMessages;
import ua.com.alevel.utilities.InputUtils;
import ua.com.alevel.utilities.TasksUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;

public class Tasks {
    public static LinkedList<Integer> stackOfTasks = new LinkedList<>();

    public static void sumOfDigitsInLine() throws IOException, ParseException {
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input)) {
            String result = TasksUtils.sumOfDigits(TasksUtils.filterDigits(input));
            MenuMessages.returnResult(input, result);
            Log.log.add(new Entry("1", input, result));
        } else if (!InputUtils.checkEmpty(input)) {
            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("1", input, "Empty Line"));
            sumOfDigitsInLine();
        } else if (!InputUtils.checkBlank(input)) {
            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("1", input, "Blank Line"));
            sumOfDigitsInLine();
        }

        stackOfTasks.add(1);
        Menu.runSubMenu();
    }

    public static void filterAndCountChars() throws IOException, ParseException {
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input)) {
            String result = TasksUtils.countLetters(TasksUtils.filterAndSortLetters(input));
            String logResult = TasksUtils.countLettersForLog(TasksUtils.filterAndSortLetters(input));
            MenuMessages.returnResult(input, result);
            Log.log.add(new Entry("2", input, logResult));
        } else if (!InputUtils.checkEmpty(input)) {
            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("2", input, "Empty Line"));
            filterAndCountChars();
        } else if (!InputUtils.checkBlank(input)) {
            ErrorLog.errorLog.add(new Entry("2", input, "Blank Line"));
            ErrorMessages.getBlankLineErrorMessage();
            filterAndCountChars();
        }

        stackOfTasks.add(2);
        Menu.runSubMenu();
    }

    public static void endLesson() throws IOException, ParseException {
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input)
                && InputUtils.checkSpelling(input)
                && InputUtils.checkRange(input)) {
            String calculation = TasksUtils.calculateLesson(input);
            String result = InputUtils.toTimeFormat(calculation);
            MenuMessages.returnResult(input, result);
            Log.log.add(new Entry("3", input, result));
        } else if (!InputUtils.checkEmpty(input)) {
            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("3", input, "Empty Line"));
            endLesson();
        } else if (!InputUtils.checkBlank(input)) {
            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("3", input, "Blank Line"));
            endLesson();
        } else if (!InputUtils.checkSpelling(input)) {
            ErrorMessages.getSpellingError();
            ErrorLog.errorLog.add(new Entry("3", input, "Not Number"));
            endLesson();
        } else if (!InputUtils.checkRange(input)) {
            ErrorMessages.getNumberRangeError();
            ErrorLog.errorLog.add(new Entry("3", input, "Not in Range"));
            endLesson();
        }

        stackOfTasks.add(3);
        Menu.runSubMenu();
    }
}
