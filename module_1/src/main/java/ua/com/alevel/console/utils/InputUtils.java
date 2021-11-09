package ua.com.alevel.console.utils;

import ua.com.alevel.levels.first.horse.ChessField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputUtils {

    public static BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));

    public static String returnLine() throws IOException {
        return inputStream.readLine();
    }

    public static boolean checkEmpty(String inputLine) {
        return !inputLine.isEmpty();
    }

    public static boolean checkBlank(String inputLine) {
        return !inputLine.isBlank();
    }

    public static boolean checkRange(String inputLine) {
        return inputLine.length() == 2;
    }

    public static ChessField convertLineToMove(String inputLine) {
        return new ChessField(inputLine.toUpperCase().charAt(0), Integer.parseInt(String.valueOf(inputLine.charAt(1))));
    }

    public static boolean checkIfNumber(String inputLine) {
        if (inputLine == null) {
            return false;
        }
        try {
            Integer.parseInt(inputLine);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean checkIfPositiveInteger(String inputLine) {
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

    public static ArrayList<Integer> parseIntegersFromInput(String input) {
        ArrayList<Integer> parsedIntegers = new ArrayList<>();
        Pattern integersPattern = Pattern.compile("(-?\\d+)",Pattern.MULTILINE);
        Matcher m = integersPattern.matcher(input);
        while (m.find()) {
            parsedIntegers.add(Integer.parseInt(m.group()));
        }
        return parsedIntegers;
    }

    public static boolean checkIfExists(int width, int height, int posVertical, int posHorizontal) {
        return posVertical>=0 && posVertical<height && posHorizontal>=0 && posHorizontal<width ;
    }
}
