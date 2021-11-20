package ua.com.alevel.console.utils;

import ua.com.alevel.console.menu.Menu;
import ua.com.alevel.console.messages.ErrorMessages;
import mathset.structures.MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Locale;

public class InputUtils {

    public static final BufferedReader INPUT = new BufferedReader(new InputStreamReader(System.in));

    public static String returnLine() throws IOException {
        return INPUT.readLine();
    }

    public static void emptyMathSets(MathSet<MathSet<Number>> mathSet) throws IOException, ParseException {
        if (mathSet.isEmpty()){
            ErrorMessages.noSetsCreatedErrorMessage();
            Menu.runConstructorsMenu();
        }
    }

    public static boolean expandableOrNot() throws IOException {
        String option = returnLine().toLowerCase(Locale.ROOT);
        boolean expandable = false;
        switch (option) {
            case "y" -> expandable = true;
            case "n" -> expandable = false;
            default -> {
                ErrorMessages.inputErrorMessage();
                expandableOrNot();
            }
        }
        return expandable;
    }
}