package ua.com.alevel.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputUtils {
    public static BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));

    public static String returnLine() throws IOException {
        return inputStream.readLine();
    }

    public static Boolean checkSpelling(String inputLine) {
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

    public static Boolean checkRange(String inputLine) {
        return Integer.parseInt(inputLine) > 0 && Integer.parseInt(inputLine) <= 10;
    }

    public static Boolean checkEmpty(String inputLine) {
        return !inputLine.isEmpty();
    }

    public static Boolean checkBlank(String inputLine) {
        return !inputLine.isBlank();
    }

    public static String toTimeFormat(String unformattedTime) throws ParseException {
        SimpleDateFormat inputTimeFormat = new SimpleDateFormat("HHmm");
        Date time = inputTimeFormat.parse(unformattedTime);
        SimpleDateFormat outputTimeFormat = new SimpleDateFormat("HH:mm");
        return outputTimeFormat.format(time);
    }
}
