package ua.com.alevel.console.utilities;

import ua.com.alevel.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class InputUtils {
    public static BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));

    public static String returnLine() throws IOException {
        return inputStream.readLine();
    }

    public static Boolean checkEmpty(String input){
        return !input.isEmpty();
    }

    public static Boolean checkBlank(String input){
        return !input.isBlank();
    }

    public static Boolean checkIfAlphabeticApostrophe(String input){
        return input.matches("^[A-Za-zА-Яа-я']+$");
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

    public static Boolean checkRange(String input){
        return Integer.parseInt(input)>0 && Integer.parseInt(input)<=120;
    }

    public static Boolean checkUserOnNull(User user){
        return user != null;
    }

    public static Boolean checkEmailPattern(String input){
        return input.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+$");
    }
}