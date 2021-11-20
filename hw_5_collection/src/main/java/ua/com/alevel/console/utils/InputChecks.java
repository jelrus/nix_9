package ua.com.alevel.console.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecks {

    public static boolean checkIfStringNumber(String inputLine) {
        if (inputLine == null) {
            return false;
        }
        try {
            int number = Integer.parseInt(inputLine);
            return number >= 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean checkIfNumber(String inputLine) {
        Pattern numberPattern = Pattern.compile("(-?\\d+(\\.\\d+)?)", Pattern.MULTILINE);
        Matcher m = numberPattern.matcher(inputLine);
        while (m.matches()) {
            return true;
        }
        return false;
    }
}