package ua.com.alevel.utils.random;

import ua.com.alevel.utils.io.TextFileReaderWriter;

import java.io.IOException;
import java.util.Random;

public class GenerateRandomDateUtil {

    private static final int YEAR_BOUNDARY_MIN = 0;
    private static final int YEAR_BOUNDARY_MAX = 2021;
    private static final int MONTH_BOUNDARY_MIN = 0;
    private static final int MONTH_BOUNDARY_MAX = 18;
    private static final int DAY_BOUNDARY_MIN = 0;
    private static final int DAY_BOUNDARY_MAX = 46;

    private static String generateRandomDatesYearMonthDaySlash() {
        StringBuilder textBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 1; i++) {
            int year = random.nextInt(YEAR_BOUNDARY_MIN, YEAR_BOUNDARY_MAX);
            int month = random.nextInt(MONTH_BOUNDARY_MIN, MONTH_BOUNDARY_MAX);
            int day = random.nextInt(DAY_BOUNDARY_MIN, DAY_BOUNDARY_MAX);
            if ((month > 0 && month < 10) && (day > 0 && day < 10)) {
                textBuilder.append(year).append("/").append("0").append(month).append("/").append("0").append(day).append("\n");
            } else if (day > 0 && day < 10) {
                textBuilder.append(year).append("/").append(month).append("/").append("0").append(day).append("\n");
            } else if (month > 0 && month < 10) {
                textBuilder.append(year).append("/").append("0").append(month).append("/").append(day).append("\n");
            } else {
                textBuilder.append(year).append("/").append(month).append("/").append(day).append("\n");
            }
        }
        return textBuilder.toString();
    }

    private static String generateRandomDatesDayMonthYearSlash() {
        StringBuilder textBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 1; i++) {
            int year = random.nextInt(YEAR_BOUNDARY_MIN, YEAR_BOUNDARY_MAX);
            int month = random.nextInt(MONTH_BOUNDARY_MIN, MONTH_BOUNDARY_MAX);
            int day = random.nextInt(DAY_BOUNDARY_MIN, DAY_BOUNDARY_MAX);
            if ((day > 0 && day < 10) && (month > 0 && month < 10)) {
                textBuilder.append("0").append(day).append("/").append("0").append(month).append("/").append(year).append("\n");
            } else if (day > 0 && day < 10) {
                textBuilder.append("0").append(day).append("/").append(month).append("/").append(year).append("\n");
            } else if (month > 0 && month < 10) {
                textBuilder.append(day).append("/").append("0").append(month).append("/").append(year).append("\n");
            } else {
                textBuilder.append(day).append("/").append(month).append("/").append(year).append("\n");
            }
        }
        return textBuilder.toString();
    }

    private static String generateRandomDatesMonthDayYearDash() {
        StringBuilder textBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 1; i++) {
            int year = random.nextInt(YEAR_BOUNDARY_MIN, YEAR_BOUNDARY_MAX);
            int month = random.nextInt(MONTH_BOUNDARY_MIN, MONTH_BOUNDARY_MAX);
            int day = random.nextInt(DAY_BOUNDARY_MIN, DAY_BOUNDARY_MAX);
            if ((day > 0 && day < 10) && (month > 0 && month < 10)) {
                textBuilder.append("0").append(month).append("-").append("0").append(day).append("-").append(year).append("\n");
            } else if (day > 0 && day < 10) {
                textBuilder.append(month).append("-").append("0").append(day).append("-").append(year).append("\n");
            } else if (month > 0 && month < 10) {
                textBuilder.append("0").append(month).append("-").append(day).append("-").append(year).append("\n");
            } else {
                textBuilder.append(month).append("-").append(day).append("-").append(year).append("\n");
            }
        }
        return textBuilder.toString();
    }

    private static String generateRandomString() {
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static void generateRandomDatesWithStringContamination(int bound) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < bound; i++) {
            if (random.nextInt(0, 100) <= 33) {
                textBuilder.append(generateRandomDatesYearMonthDaySlash());
            } else if (random.nextInt(0, 100) <= 33) {
                textBuilder.append(generateRandomDatesDayMonthYearSlash());
            } else {
                textBuilder.append(generateRandomDatesMonthDayYearDash());
            }
            if (random.nextInt(0, 100) <= 50) {
                textBuilder.append(generateRandomString()).append("\n");
            }
        }
        TextFileReaderWriter.stringToFile(textBuilder.toString(), TextFileReaderWriter.DATES_FILE_INPUT_PATH);
    }
}