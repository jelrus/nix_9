package ua.com.alevel.tasks;

import ua.com.alevel.utils.io.TextFileReaderWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Dates {

    private static boolean isYearMonthDayWithSlash(String checked) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate.parse(checked, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isDayMonthYearWithSlash(String checked) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(checked, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isMonthDayYearWithDash(String checked) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate.parse(checked, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean daysAreValidYearMonthDayWithSlash(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        if (month > 0 && month <= 12) {
            if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                    && (day > 0 && day <= 31)) {
                return true;
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 0 && day <= 30)) {
                return true;
            } else if (year % 4 == 0 && month == 2 && (day > 0 && day <= 29)) {
                return true;
            } else if (year % 4 != 0 && month == 2 && (day > 0 && day <= 28)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean daysAreValidDayMonthYearWithSlash(String date) {
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));
        if (month > 0 && month <= 12) {
            if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                    && (day > 0 && day <= 31)) {
                return true;
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 0 && day <= 30)) {
                return true;
            } else if (year % 4 == 0 && month == 2 && (day > 0 && day <= 29)) {
                return true;
            } else if (year % 4 != 0 && month == 2 && (day > 0 && day <= 28)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean daysAreValidMonthDayYearWithDash(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));
        if (month > 0 && month <= 12) {
            if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                    && (day > 0 && day <= 31)) {
                return true;
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 0 && day <= 30)) {
                return true;
            } else if (year % 4 == 0 && month == 2 && (day > 0 && day <= 29)) {
                return true;
            } else if (year % 4 != 0 && month == 2 && (day > 0 && day <= 28)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean dateIsValid(String checked) {
        boolean firstGroupIsValid = isYearMonthDayWithSlash(checked) && daysAreValidYearMonthDayWithSlash(checked);
        boolean secondGroupIsValid = isDayMonthYearWithSlash(checked) && daysAreValidDayMonthYearWithSlash(checked);
        boolean thirdGroupIsValid = isMonthDayYearWithDash(checked) && daysAreValidMonthDayYearWithDash(checked);
        return firstGroupIsValid || secondGroupIsValid || thirdGroupIsValid;
    }

    private static List<String> filterDates(List<String> text) {
        List<String> filteredText = new ArrayList<>();
        for (String line : text) {
            if (dateIsValid(line)) {
                filteredText.add(line);
            }
        }
        return filteredText;
    }


    private static String removeDelimiters(List<String> text) {
        StringBuilder textBuilder = new StringBuilder();
        for (String line : text) {
            if (line.contains("/")) {
                textBuilder.append(line.replaceAll("/", ""));
                textBuilder.append("\n");
            } else if (line.contains("-")) {
                textBuilder.append(line.replaceAll("-", ""));
                textBuilder.append("\n");
            }
        }
        return textBuilder.toString();
    }

    public static void writeFilteringResult(String inputPath, String outputPath) throws IOException {
        List<String> initialText = TextFileReaderWriter.fileToList(inputPath);
        String outputText = removeDelimiters(filterDates(initialText));
        TextFileReaderWriter.stringToFile(outputText, outputPath);
    }
}