package ua.com.alevel.utilities;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class TasksUtils {
    public static ArrayList<Integer> filterDigits(String inputLine) {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 0; i < inputLine.length(); i++) {
            if (Character.isDigit(inputLine.toCharArray()[i])) {
                digits.add(Integer.parseInt(String.valueOf(inputLine.toCharArray()[i])));
            }
        }
        return digits;
    }

    public static String sumOfDigits(ArrayList<Integer> digits) {
        Integer result = 0;
        for (Integer digit : digits) {
            result += digit;
        }
        return String.valueOf(result);
    }

    public static TreeMap<String, Integer> filterAndSortLetters(String inputLine) {
        TreeMap<String, Integer> letters = new TreeMap<>();
        for (int i = 0; i < inputLine.length(); i++) {
            if (Character.isAlphabetic(inputLine.toCharArray()[i])) {
                letters.put(String.valueOf(inputLine.charAt(i)),
                        letters.getOrDefault(String.valueOf(inputLine.charAt(i)), 0) + 1);
            }
        }
        return letters;
    }

    public static String countLetters(TreeMap<String, Integer> letters) {
        int lettersCounter = 1;
        StringBuilder result = new StringBuilder("\n");
        for (Map.Entry<String, Integer> letter : letters.entrySet()) {
            result.append(lettersCounter++).append(". ")
                    .append(letter.getKey()).append(" - ")
                    .append(letter.getValue()).append("\n");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public static String countLettersForLog(TreeMap<String, Integer> letters) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> letter : letters.entrySet()) {
            result.append(letter.getKey()).append(" - ").append(letter.getValue()).append(", ");
        }
        result.delete(result.length() - 2, result.length() - 1);
        return result.toString();
    }

    public static String calculateLesson(String inputLine) {
        Integer lessonNumber = Integer.parseInt(inputLine);
        Integer time = (9 * 60) + (45 * lessonNumber) + ((lessonNumber / 2) * 5) + (((lessonNumber - 1) / 2) * 15);
        Integer endHours = time / 60;
        Integer endMinutes = time % 60;
        return endHours + " " + endMinutes;
    }
}
