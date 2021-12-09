package ua.com.alevel.controller.utils;

import calendar.components.composite.Calendar;
import calendar.utils.parser.CalendarFormatter;
import ua.com.alevel.controller.menu.Menu;
import ua.com.alevel.controller.messages.ErrorMessages;
import ua.com.alevel.controller.messages.MenuMessages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CalendarHelper {

    public static CalendarFormatter formatter = new CalendarFormatter("dd/mm/yyyy 00:00:00.000");
    public static CalendarFormatter parser = new CalendarFormatter("dd/mm/yyyy 00:00:00.000");
    public static Calendar calendar = new Calendar();
    public static List<Calendar> calendars = new ArrayList<>();
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void createOrChangeInitialCalendar() throws IOException, ParseException {
        Menu.subMenusTasks.add(1);
        MenuMessages.calendarInput();
        MenuMessages.viewInputFormat(parser.getCalendar());
        String inputLine = reader.readLine();
        if (inputLine.length() == parser.getCalendar().length()) {
            calendar = parser.parse(inputLine);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        MenuMessages.calendarOutput(calendar);
        Menu.returnToSubMenu();
    }

    public static void changeInputFormat() throws IOException, ParseException {
        Menu.subMenusTasks.add(2);
        MenuMessages.formatInput();
        String oldParseFormat = parser.getCalendar();
        parser = new CalendarFormatter(reader.readLine());
        MenuMessages.formatInputResult(oldParseFormat, parser.getCalendar());
        Menu.returnToSubMenu();
    }

    public static void changeOutputFormat() throws IOException, ParseException {
        Menu.subMenusTasks.add(3);
        MenuMessages.formatOutput();
        String oldFormat = formatter.getCalendar();
        formatter = new CalendarFormatter(reader.readLine());
        MenuMessages.formatOutputResult(oldFormat, formatter.getCalendar());
        Menu.returnToSubMenu();
    }

    public static void appendMillis() throws IOException, ParseException {
        Menu.subMenusTasks.add(4);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.millisAppendInput();
        String inputMillis = reader.readLine();
        if (checkIfPositiveInteger(inputMillis)) {
            int millisInput = Integer.parseInt(inputMillis);
            calendar.appendMillis(millisInput);
            String newCalendar = formatter.format(calendar);
            MenuMessages.dateResult(newCalendar);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void appendSeconds() throws IOException, ParseException {
        Menu.subMenusTasks.add(5);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.secondsAppendInput();
        String inputSeconds = reader.readLine();
        if (checkIfPositiveInteger(inputSeconds)) {
            int secondsInput = Integer.parseInt(inputSeconds);
            calendar.appendSeconds(secondsInput);
            String newCalendar = formatter.format(calendar);
            MenuMessages.dateResult(newCalendar);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void appendMinutes() throws IOException, ParseException {
        Menu.subMenusTasks.add(6);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.minutesAppendInput();
        String inputMinutes = reader.readLine();
        if (checkIfPositiveInteger(inputMinutes)) {
            int minutesInput = Integer.parseInt(inputMinutes);
            calendar.appendMinutes(minutesInput);
            String newCalendar = formatter.format(calendar);
            MenuMessages.dateResult(newCalendar);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void appendHours() throws IOException, ParseException {
        Menu.subMenusTasks.add(7);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.hoursAppendInput();
        String inputHours = reader.readLine();
        if (checkIfPositiveInteger(inputHours)) {
            int hoursInput = Integer.parseInt(inputHours);
            calendar.appendHours(hoursInput);
            String newCalendar = formatter.format(calendar);
            MenuMessages.dateResult(newCalendar);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void appendDays() throws IOException, ParseException {
        Menu.subMenusTasks.add(8);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.daysAppendInput();
        String inputDays = reader.readLine();
        if (checkIfPositiveInteger(inputDays)) {
            int daysInput = Integer.parseInt(inputDays);
            calendar.appendDays(daysInput);
            String newCalendar = formatter.format(calendar);
            MenuMessages.dateResult(newCalendar);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void appendYears() throws IOException, ParseException {
        Menu.subMenusTasks.add(9);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.yearsAppendInput();
        String inputYears = reader.readLine();
        if (checkIfPositiveInteger(inputYears)) {
            int yearsInput = Integer.parseInt(inputYears);
            calendar.appendYears(yearsInput);
            String newCalendar = formatter.format(calendar);
            MenuMessages.dateResult(newCalendar);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void subtractMillis() throws IOException, ParseException {
        Menu.subMenusTasks.add(10);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.millisSubtractInput();
        String inputMillis = reader.readLine();
        if (checkIfPositiveInteger(inputMillis)) {
            int millisInput = Integer.parseInt(inputMillis);
            calendar.subtractMillis(millisInput);
            String newCalendar = formatter.format(calendar);
            MenuMessages.dateResult(newCalendar);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void subtractSeconds() throws IOException, ParseException {
        Menu.subMenusTasks.add(11);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.secondsSubtractInput();
        String inputSeconds = reader.readLine();
        if (checkIfPositiveInteger(inputSeconds)) {
            int secondsInput = Integer.parseInt(inputSeconds);
            calendar.subtractSeconds(secondsInput);
            String newCalendar = formatter.format(calendar);
            MenuMessages.dateResult(newCalendar);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void subtractMinutes() throws IOException, ParseException {
        Menu.subMenusTasks.add(12);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.minutesSubtractInput();
        String inputMinutes = reader.readLine();
        if (checkIfPositiveInteger(inputMinutes)) {
            int minutesInput = Integer.parseInt(inputMinutes);
            calendar.subtractMinutes(minutesInput);
            String newCalendar = formatter.format(calendar);
            MenuMessages.dateResult(newCalendar);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void subtractHours() throws IOException, ParseException {
        Menu.subMenusTasks.add(13);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.hoursSubtractInput();
        String inputDays = reader.readLine();
        if (checkIfPositiveInteger(inputDays)) {
            int daysInput = Integer.parseInt(inputDays);
            calendar.subtractHours(daysInput);
            String newCalendar = formatter.format(calendar);
            MenuMessages.dateResult(newCalendar);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void subtractDays() throws IOException, ParseException {
        Menu.subMenusTasks.add(14);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.daysSubtractInput();
        String inputDays = reader.readLine();
        if (checkIfPositiveInteger(inputDays)) {
            int daysInput = Integer.parseInt(inputDays);
            calendar.subtractDays(daysInput);
            String newCalendar = formatter.format(calendar);
            MenuMessages.dateResult(newCalendar);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void subtractYears() throws IOException, ParseException {
        Menu.subMenusTasks.add(15);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.yearsSubtractInput();
        String inputYears = reader.readLine();
        if (checkIfPositiveInteger(inputYears)) {
            int yearsInput = Integer.parseInt(inputYears);
            calendar.subtractYears(yearsInput);
            String newCalendar = formatter.format(calendar);
            MenuMessages.dateResult(newCalendar);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void distinctMillis() throws IOException, ParseException {
        Menu.subMenusTasks.add(16);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.viewInputFormat(formatter.getCalendar());
        MenuMessages.newDate();
        String inputLine = reader.readLine();
        if (inputLine.length() == parser.getCalendar().length()) {
            Calendar target = parser.parse(inputLine);
            Double millis = calendar.distinctByMillis(target);
            String millisString = Math.abs(millis.longValue()) + "";
            MenuMessages.millisDistinctionResult(calendar, target, millisString);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void distinctSeconds() throws IOException, ParseException {
        Menu.subMenusTasks.add(17);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.viewInputFormat(formatter.getCalendar());
        MenuMessages.newDate();
        String inputLine = reader.readLine();
        if (inputLine.length() == parser.getCalendar().length()) {
            Calendar target = parser.parse(inputLine);
            Double seconds = calendar.distinctBySeconds(target);
            String secondsString = Math.abs(seconds.longValue()) + "";
            MenuMessages.secondsDistinctionResult(calendar, target, secondsString);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void distinctMinutes() throws IOException, ParseException {
        Menu.subMenusTasks.add(18);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.viewInputFormat(formatter.getCalendar());
        MenuMessages.newDate();
        String inputLine = reader.readLine();
        if (inputLine.length() == parser.getCalendar().length()) {
            Calendar target = parser.parse(inputLine);
            Double minutes = calendar.distinctByMinutes(target);
            String minutesString = Math.abs(minutes.longValue()) + "";
            MenuMessages.minutesDistinctionResult(calendar, target, minutesString);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void distinctHours() throws IOException, ParseException {
        Menu.subMenusTasks.add(19);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.viewInputFormat(formatter.getCalendar());
        MenuMessages.newDate();
        String inputLine = reader.readLine();
        if (inputLine.length() == parser.getCalendar().length()) {
            Calendar target = parser.parse(inputLine);
            Double hours = calendar.distinctByHours(target);
            String hoursString = Math.abs(hours.longValue()) + "";
            MenuMessages.hoursDistinctionResult(calendar, target, hoursString);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void distinctDays() throws IOException, ParseException {
        Menu.subMenusTasks.add(20);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.viewInputFormat(formatter.getCalendar());
        MenuMessages.newDate();
        String inputLine = reader.readLine();
        if (inputLine.length() == parser.getCalendar().length()) {
            Calendar target = parser.parse(inputLine);
            Double days = calendar.distinctByDays(target);
            String daysString = Math.abs(days.longValue()) + "";
            MenuMessages.daysDistinctionResult(calendar, target, daysString);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void distinctYears() throws IOException, ParseException {
        Menu.subMenusTasks.add(21);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.viewInputFormat(formatter.getCalendar());
        MenuMessages.newDate();
        String inputLine = reader.readLine();
        if (inputLine.length() == parser.getCalendar().length()) {
            Calendar target = parser.parse(inputLine);
            Double years = calendar.distinctByYears(target);
            String yearsString = Math.abs(years.longValue()) + "";
            MenuMessages.yearsDistinctionResult(calendar, target, yearsString);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void distinct() throws IOException, ParseException {
        Menu.subMenusTasks.add(22);
        MenuMessages.currentCalendar(calendar);
        MenuMessages.viewInputFormat(formatter.getCalendar());
        MenuMessages.newDate();
        String inputLine = reader.readLine();
        if (inputLine.length() == parser.getCalendar().length()) {
            Calendar target = parser.parse(inputLine);
            String date = calendar.distinct(target);
            MenuMessages.dateDistinctionResult(date);
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void createList() throws IOException, ParseException {
        Menu.subMenusTasks.add(23);
        MenuMessages.viewInputFormat(formatter.getCalendar());
        MenuMessages.datesNumber();
        String numberInput = reader.readLine();
        if (checkIfPositiveInteger(numberInput)) {
            int number = Integer.parseInt(numberInput);
            for (int i = 0; i < number; i++) {
                MenuMessages.enterDate(i + 1);
                String inputLine = reader.readLine();
                if (inputLine.length() == parser.getCalendar().length()) {
                    Calendar target = parser.parse(inputLine);
                    calendars.add(target);
                } else {
                    ErrorMessages.inputErrorMessage();
                    Menu.returnToSubMenu();
                }
            }
        } else {
            ErrorMessages.inputErrorMessage();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static void sortListAsc() throws IOException, ParseException {
        Menu.subMenusTasks.add(24);
        Calendar.sortCalendarsAsc(calendars);
        MenuMessages.sortAscending();
        Menu.returnToSubMenu();
    }

    public static void sortListDesc() throws IOException, ParseException {
        Menu.subMenusTasks.add(25);
        Calendar.sortCalendarsDesc(calendars);
        MenuMessages.sortDescending();
        Menu.returnToSubMenu();
    }

    public static void showList() throws IOException, ParseException {
        Menu.subMenusTasks.add(26);
        if (!calendars.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (Calendar calendar : calendars) {
                builder.append(calendar).append("\n");
            }
            MenuMessages.showAll(builder.toString());
        } else {
            ErrorMessages.listIsEmpty();
            Menu.returnToSubMenu();
        }
        Menu.returnToSubMenu();
    }

    public static boolean checkIfPositiveInteger(String input) {
        if (input == null) {
            return false;
        }
        try {
            int number = Integer.parseInt(input);
            return number >= 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}