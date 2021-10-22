package stringutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringReverse {
    private StringReverse(){}

    public static String reverseString(String input){
        StringBuilder output = new StringBuilder();
        if (input.toCharArray().length<=1){
            return input;
        }

        for (int i = input.length()-1; i>=0; i--){
            output.append(input.toCharArray()[i]);
        }
        return output.toString();
    }

    public static String reverseSequenceWithFirstCharacter(String input, char symbol){
        StringBuilder output = new StringBuilder();
        StringBuilder splitter = new StringBuilder();

        ArrayList<String> splitResult = new ArrayList<>(List.of(input.split("")));
        int charPosition = splitResult.indexOf(String.valueOf(symbol));

        for (int i = 0; i<splitResult.size(); i++){
            if (i<charPosition){
                output.append(splitResult.get(i));
            } else {
                splitter.append(splitResult.get(i));
            }
        }

        return output.append(StringReverse.reverseString(splitter.toString())).toString();
    }

    public static String reverseSequenceAfterFirstCharacter(String input, char symbol){
        StringBuilder output = new StringBuilder();
        StringBuilder splitter = new StringBuilder();

        ArrayList<String> splitResult = new ArrayList<>(List.of(input.split("")));
        int charPosition = splitResult.indexOf(String.valueOf(symbol));

        for (int i = 0; i<splitResult.size(); i++){
            if (i<charPosition+1){
                output.append(splitResult.get(i));
            } else {
                splitter.append(splitResult.get(i));
            }
        }

        return output.append(StringReverse.reverseString(splitter.toString())).toString();
    }

    public static String reverseStringFromIndex(String input, int begin){
        StringBuilder output = new StringBuilder();
        StringBuilder splitterBegin = new StringBuilder();

        ArrayList<String> splitResult = new ArrayList<>(List.of(input.split("")));

        for(int i = 0; i<splitResult.size(); i++){
            if (i<begin){
                splitterBegin.append(splitResult.get(i));
            } else {
                output.append(splitResult.get(i));
            }
        }

        return splitterBegin + StringReverse.reverseString(output.toString());
    }

    public static String reverseStringByIndexes(String input, int begin, int end){
        StringBuilder output = new StringBuilder();
        StringBuilder splitterBegin = new StringBuilder();
        StringBuilder splitterEnd = new StringBuilder();

        ArrayList<String> splitResult = new ArrayList<>(List.of(input.split("")));

        for(int i = 0; i<splitResult.size(); i++){
            if (i<begin){
                splitterBegin.append(splitResult.get(i));
            } else if (i<=end){
                output.append(splitResult.get(i));
            } else {
                splitterEnd.append(splitResult.get(i));
            }
        }

        return splitterBegin + StringReverse.reverseString(output.toString()) + splitterEnd;
    }

    public static String reverseCharSequence(String input, CharSequence symbols){
        StringBuilder output = new StringBuilder();
        ArrayList<String> splitString = new ArrayList<>(List.of(input.split(symbols.toString())));
        splitString.add("");

        for(int i = 0; i<splitString.size(); i++){
            if (i%2 != 0){
                splitString.add(i, StringReverse.reverseString(symbols.toString()));
            }
            output.append(splitString.get(i));
        }

        if (output.length()>input.length()){
            output.delete(input.length(), output.length());
        }
        return output.toString();
    }

    public static String reverseSubstring(String input, String subsequence){
        StringBuilder output = new StringBuilder();
        ArrayList<String> splitString = new ArrayList<>(List.of(input.split(subsequence)));
        splitString.add("");
        for(int i = 0; i<splitString.size(); i++){
            if (i%2 != 0){
                splitString.add(i, StringReverse.reverseString(subsequence));
            }
            output.append(splitString.get(i));
        }

        if (output.length()>input.length()){
            output.delete(input.length(), output.length());
        }
        return output.toString();
    }

    public static String reverseSequencesWithCharacter(String input, char symbol){
        StringBuilder splitter = new StringBuilder();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i<input.length(); i++){
            if (input.toCharArray()[i] == symbol){
                splitter.append(",").append(input.toCharArray()[i]);
            } else {
                splitter.append(input.toCharArray()[i]);
            }
        }

        ArrayList<String> splitResult = new ArrayList<>(List.of(splitter.toString().split(",")));
        for (String s : splitResult) {
            if (s.contains(String.valueOf(symbol))) {
                output.append(StringReverse.reverseString(s));
            } else {
                output.append(s);
            }
        }
        return output.toString();
    }

    public static String reverseSequencesAfterCharacter(String input, char symbol){
        StringBuilder splitter = new StringBuilder();
        StringBuilder output = new StringBuilder();

        String[] charSplit = input.split("");

        for (String s : charSplit) {
            if (Objects.equals(s, String.valueOf(symbol))) {
                splitter.append(",").append(s).append(",");
            } else {
                splitter.append(s);
            }
        }
        ArrayList<String> splitResult = new ArrayList<>(List.of(splitter.toString().split(",")));

        for (int i = 0; i<splitResult.size(); i++){
            if (i == 0){
                output.append(splitResult.get(i));
            } else if (Objects.equals(splitResult.get(i), String.valueOf(symbol))){
                output.append(splitResult.get(i));
            } else {
                output.append(StringReverse.reverseString(splitResult.get(i)));
            }
        }
        return output.toString();
    }

    public static String reverseSubstringsFromIndex(String input, int begin){
        StringBuilder output = new StringBuilder();
        String[] substrings = input.split(" ");

        for (int i = 0; i<substrings.length; i++){
            if (i >= begin){
                output.append(StringReverse.reverseString(substrings[i])).append(" ");
            } else {
                output.append(substrings[i]).append(" ");
            }
        }
        return output.toString();
    }

    public static String reverseSubstringsByIndexes(String input, int begin, int end){
        StringBuilder output = new StringBuilder();
        String[] substrings = input.split(" ");

        for (int i = 0; i<substrings.length; i++){
            if (i >= begin && i<=end){
                output.append(StringReverse.reverseString(substrings[i])).append(" ");
            } else {
                output.append(substrings[i]).append(" ");
            }
        }
        return output.toString();
    }
}
