package ua.com.alevel.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class InputUtils {
    public static BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));

    public static String returnLine() throws IOException {
        return inputStream.readLine();
    }

    public static Boolean checkContainsSequence(String input, String checkedSequence){
        return input.contains(checkedSequence);
    }

    public static Boolean checkContainsSymbol(String input, char symbol){
        return input.contains(String.valueOf(symbol));
    }

    public static Boolean checkContainsSequence(String input, CharSequence charSequence){
        return input.contains(String.valueOf(charSequence));
    }

    public static Boolean checkIfNumber(String input){
        if (input == null) {
            return false;
        } try {
            Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static Boolean checkInputCharRange(String inputChar){
        return inputChar.length() <= 1;
    }

    public static Boolean checkStringBeginRange(String input, int begin){
        return begin>=0 && begin<input.toCharArray().length;
    }

    public static Boolean checkStringRangeBetween(int begin, int end){
        return begin<=end;
    }

    public static Boolean checkStringEndRange(String input, int end){
        return end>=0 && end<input.toCharArray().length;
    }

    public static Boolean checkSubsequenceBeginRange(String input, int begin){
        return begin>=0 && begin<input.split(" ").length;
    }

    public static Boolean checkSubsequenceRangeBetween(int begin, int end){
        return begin<=end;
    }

    public static Boolean checkSubsequenceEndRange(String input, int end){
        return end>=0 && end<input.split(" ").length;
    }

    public static Boolean checkEmpty(String input){
        return !input.isEmpty();
    }

    public static Boolean checkBlank(String input){
        return !input.isBlank();
    }
}
