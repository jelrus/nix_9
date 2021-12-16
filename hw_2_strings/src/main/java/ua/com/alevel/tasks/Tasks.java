package ua.com.alevel.tasks;

import stringutils.StringReverse;
import ua.com.alevel.log.ErrorLog;
import ua.com.alevel.log.Log;
import ua.com.alevel.log.entries.Entry;
import ua.com.alevel.controller.menu.Menu;
import ua.com.alevel.messages.ErrorMessages;
import ua.com.alevel.messages.MenuMessages;
import ua.com.alevel.utilities.InputUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;

public class Tasks {
    public static LinkedList<Integer> stackOfTasks = new LinkedList<>();

    public static void reverseFullString() throws IOException, ParseException {
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input)){

            String output = StringReverse.reverseString(input);
            MenuMessages.returnResult(input, output);
            Log.log.add(new Entry("1",input,output));

        } else if (!InputUtils.checkEmpty(input)){

            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("1",input,"Empty Input Line"));
            reverseFullString();

        } else if (!InputUtils.checkBlank(input)){

            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("1", input, "Blank Input Line"));
            reverseFullString();
        }
        stackOfTasks.add(1);
        Menu.runSubMenu();
    }

    public static void reverseSequenceWithFirstCharacter() throws IOException, ParseException {
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();
        MenuMessages.getInputCharMessage();
        String inputChar = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input) &&
                InputUtils.checkEmpty(inputChar) && InputUtils.checkBlank(inputChar) &&
                InputUtils.checkInputCharRange(inputChar) &&
                InputUtils.checkContainsSymbol(input,inputChar.charAt(0))){

            String output = StringReverse.reverseSequenceWithFirstCharacter(input, inputChar.charAt(0));
            MenuMessages.returnResult(input + ", " + inputChar,output);
            Log.log.add(new Entry("2",
                    input + ", " + inputChar,
                    output));

        } else if (!InputUtils.checkEmpty(input)){

            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("2",
                    input + ", " + inputChar ,
                    "Empty Input Line"));
            reverseSequenceWithFirstCharacter();

        } else if (!InputUtils.checkBlank(input)){

            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("2",
                    input + ", " + inputChar ,
                    "Blank Input Line"));
            reverseSequenceWithFirstCharacter();

        } else if (!InputUtils.checkEmpty(inputChar)){

            ErrorMessages.getEmptyCharLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("2",
                    input + ", " + inputChar ,
                    "Blank Character Input Line"));
            reverseSequenceWithFirstCharacter();

        } else if (!InputUtils.checkBlank(inputChar)){

            ErrorMessages.getBlankCharLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("2",
                    input + ", " + inputChar ,
                    "Empty Character Input Line"));
            reverseSequenceWithFirstCharacter();

        } else if (!InputUtils.checkInputCharRange(inputChar)){

            ErrorMessages.getCharacterFormatError();
            ErrorLog.errorLog.add(new Entry("2",
                    input + ", " + inputChar ,
                    "Input Line is too long for character"));
            reverseSequenceWithFirstCharacter();

        } else if (!InputUtils.checkContainsSymbol(input,inputChar.charAt(0))){

            ErrorMessages.getStringNotContainsCharacterError();
            ErrorLog.errorLog.add(new Entry("2",
                    input + ", " + inputChar ,
                    "Input Line doesn't contain this character"));
            reverseSequenceWithFirstCharacter();

        }
        stackOfTasks.add(2);
        Menu.runSubMenu();
    }

    public static void reverseSequenceAfterFirstCharacter() throws IOException, ParseException{
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();
        MenuMessages.getInputCharMessage();
        String inputChar = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input) &&
                InputUtils.checkEmpty(inputChar) && InputUtils.checkBlank(inputChar) &&
                InputUtils.checkInputCharRange(inputChar) &&
                InputUtils.checkContainsSymbol(input,inputChar.charAt(0))){

            String output = StringReverse.reverseSequenceAfterFirstCharacter(input, inputChar.charAt(0));
            MenuMessages.returnResult(input + ", " + inputChar,output);
            Log.log.add(new Entry("3",
                    input + ", " + inputChar,
                    output));

        } else if (!InputUtils.checkEmpty(input)){

            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("3",
                    input + ", " + inputChar ,
                    "Empty Input Line"));
            reverseSequenceAfterFirstCharacter();

        } else if (!InputUtils.checkBlank(input)){

            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("3",
                    input + ", " + inputChar ,
                    "Blank Input Line"));
            reverseSequenceAfterFirstCharacter();

        } else if (!InputUtils.checkEmpty(inputChar)){

            ErrorMessages.getEmptyCharLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("3",
                    input + ", " + inputChar ,
                    "Blank Character Input Line"));
            reverseSequenceAfterFirstCharacter();

        } else if (!InputUtils.checkBlank(inputChar)){

            ErrorMessages.getBlankCharLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("3",
                    input + ", " + inputChar ,
                    "Empty Character Input Line"));
            reverseSequenceAfterFirstCharacter();

        } else if (!InputUtils.checkInputCharRange(inputChar)){

            ErrorMessages.getCharacterFormatError();
            ErrorLog.errorLog.add(new Entry("3",
                    input + ", " + inputChar ,
                    "Input Line is too long for character"));
            reverseSequenceAfterFirstCharacter();

        } else if (!InputUtils.checkContainsSymbol(input,inputChar.charAt(0))){

            ErrorMessages.getStringNotContainsCharacterError();
            ErrorLog.errorLog.add(new Entry("3",
                    input + ", " + inputChar ,
                    "Input Line doesn't contain this character"));
            reverseSequenceAfterFirstCharacter();

        }
        stackOfTasks.add(3);
        Menu.runSubMenu();
    }

    public static void reverseStringFromIndex() throws IOException, ParseException{
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();
        MenuMessages.getInputBeginIndexMessage();
        String inputIndex = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input) &&
                InputUtils.checkEmpty(inputIndex) && InputUtils.checkBlank(inputIndex) &&
                InputUtils.checkIfNumber(inputIndex) &&
                InputUtils.checkStringBeginRange(input,Integer.parseInt(inputIndex))){

            String output = StringReverse.reverseStringFromIndex(input, Integer.parseInt(inputIndex));
            MenuMessages.returnResult(input + ", " + inputIndex, output);
            Log.log.add(new Entry("4",input + ", " + inputIndex,output));

        } else if (!InputUtils.checkEmpty(input)){

            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("4",input,"Empty Input String Line"));
            reverseStringFromIndex();

        } else if (!InputUtils.checkBlank(input)) {

            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("4",input,"Blank Input String Line"));
            reverseStringFromIndex();

        } else if (!InputUtils.checkEmpty(inputIndex)){

            ErrorMessages.getEmptyIndexLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("4",input,"Empty Input Index Line"));
            reverseStringFromIndex();

        } else if (!InputUtils.checkBlank(inputIndex)){

            ErrorMessages.getBlankIndexLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("4",input,"Blank Input Index Line"));
            reverseStringFromIndex();

        } else if (!InputUtils.checkIfNumber(inputIndex)){

            ErrorMessages.getNumberFormatError();
            ErrorLog.errorLog.add(new Entry("4",input,"Index Not a Number"));
            reverseStringFromIndex();

        } else if (!InputUtils.checkStringBeginRange(input,Integer.parseInt(inputIndex))){

            ErrorMessages.getBeginIndexOutOfBoundsError();
            ErrorLog.errorLog.add(new Entry("4",input,"Index Out of Bounds"));
            reverseStringFromIndex();
        }
        stackOfTasks.add(4);
        Menu.runSubMenu();
    }

    public static void reverseStringByIndexes() throws IOException, ParseException {
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();
        MenuMessages.getInputBeginIndexMessage();
        String inputBeginIndex = InputUtils.returnLine();
        MenuMessages.getInputEndIndexMessage();
        String inputEndIndex = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input) &&

                InputUtils.checkEmpty(inputBeginIndex) && InputUtils.checkBlank(inputBeginIndex) &&
                InputUtils.checkIfNumber(inputBeginIndex) &&
                InputUtils.checkStringBeginRange(input,Integer.parseInt(inputBeginIndex)) &&

                InputUtils.checkEmpty(inputEndIndex) && InputUtils.checkBlank(inputEndIndex) &&
                InputUtils.checkIfNumber(inputEndIndex) &&
                InputUtils.checkStringBeginRange(input,Integer.parseInt(inputEndIndex)) &&

                InputUtils.checkStringRangeBetween(Integer.parseInt(inputBeginIndex),
                        Integer.parseInt(inputEndIndex))){

            String output = StringReverse.reverseStringByIndexes(input,
                    Integer.parseInt(inputBeginIndex),
                    Integer.parseInt(inputEndIndex));
            MenuMessages.returnResult(input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    output);
            Log.log.add(new Entry("5",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    output));

        } else if (!InputUtils.checkEmpty(input)){

            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("5",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Empty Input String Line"));
            reverseStringByIndexes();

        } else if (!InputUtils.checkBlank(input)) {

            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("5",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Blank Input String Line"));
            reverseStringByIndexes();

        } else if (!InputUtils.checkEmpty(inputBeginIndex)) {

            ErrorMessages.getEmptyBeginIndexLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("5",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Empty Input Index Line"));
            reverseStringByIndexes();

        } else if (!InputUtils.checkBlank(inputBeginIndex)) {

            ErrorMessages.getBlankBeginIndexLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("5",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Blank Input Index Line"));
            reverseStringByIndexes();

        } else if (!InputUtils.checkIfNumber(inputBeginIndex)) {

            ErrorMessages.getNumberFormatError();
            ErrorLog.errorLog.add(new Entry("5",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Begin Index is not a number"));
            reverseStringByIndexes();

        } else if (!InputUtils.checkStringBeginRange(input, Integer.parseInt(inputBeginIndex))){

            ErrorMessages.getBeginIndexOutOfBoundsError();
            ErrorLog.errorLog.add(new Entry("5",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Begin Index is out of bounds"));
            reverseStringByIndexes();

        } else if (!InputUtils.checkEmpty(inputEndIndex)) {

            ErrorMessages.getEmptyEndIndexLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("5",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Empty Input Index Line"));
            reverseStringByIndexes();

        } else if (!InputUtils.checkBlank(inputEndIndex)) {

            ErrorMessages.getBlankEndIndexLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("5",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Blank Input Index Line"));
            reverseStringByIndexes();

        } else if (!InputUtils.checkIfNumber(inputEndIndex)) {

            ErrorMessages.getNumberFormatError();
            ErrorLog.errorLog.add(new Entry("5",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "End Index is not a number"));
            reverseStringByIndexes();

        } else if (!InputUtils.checkStringEndRange(input, Integer.parseInt(inputEndIndex))){

            ErrorMessages.getEndIndexOutOfBoundsError();
            ErrorLog.errorLog.add(new Entry("5",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "End Index is out of bounds"));
            reverseStringByIndexes();

        } else if (!InputUtils.checkStringRangeBetween(Integer.parseInt(inputBeginIndex),
                Integer.parseInt(inputEndIndex))){

            ErrorMessages.getBeginIndexGreaterThanEndIndexError();
            ErrorLog.errorLog.add(new Entry("5",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Begin Index greater than End Index"));
            reverseStringByIndexes();

        }
        stackOfTasks.add(5);
        Menu.runSubMenu();
    }

    public static void reverseCharSequence() throws IOException, ParseException{
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();
        MenuMessages.getInputSubsequenceMessage();
        String inputSubsequence = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input) &&
                InputUtils.checkEmpty(inputSubsequence) && InputUtils.checkBlank(inputSubsequence) &&
                InputUtils.checkContainsSequence(input,inputSubsequence)){

            String output = StringReverse.reverseCharSequence(input,inputSubsequence);
            MenuMessages.returnResult(input + ", " + inputSubsequence, output);
            Log.log.add(new Entry("6",input + ", " + inputSubsequence, output));

        } else if (!InputUtils.checkEmpty(input)){

            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("6",input,"Empty Input Line"));
            reverseCharSequence();

        } else if (!InputUtils.checkBlank(input)){

            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("6",input,"Blank Input Line"));
            reverseCharSequence();

        } else if (!InputUtils.checkEmpty(inputSubsequence)){

            ErrorMessages.getEmptySubsequenceLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("6",input,"Empty Subsequence Input Line"));
            reverseCharSequence();

        } else if (!InputUtils.checkBlank(inputSubsequence)){

            ErrorMessages.getBlankSubsequenceLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("6",input,"Blank Subsequence Input Line"));
            reverseCharSequence();

        } else if (!InputUtils.checkContainsSequence(input,inputSubsequence)){

            ErrorMessages.getStringNotContainsSubsequenceError();
            ErrorLog.errorLog.add(new Entry("6",input,"String doesn't contain this subsequence"));
            reverseCharSequence();

        }
        stackOfTasks.add(6);
        Menu.runSubMenu();
    }

    public static void reverseSubstring() throws IOException, ParseException {
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();
        MenuMessages.getInputSubsequenceMessage();
        String inputSubsequence = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input) &&
                InputUtils.checkEmpty(inputSubsequence) && InputUtils.checkBlank(inputSubsequence) &&
                InputUtils.checkContainsSequence(input,inputSubsequence)){

            String output = StringReverse.reverseSubstring(input,inputSubsequence);
            MenuMessages.returnResult(input + ", " + inputSubsequence, output);
            Log.log.add(new Entry("7",input + ", " + inputSubsequence, output));

        } else if (!InputUtils.checkEmpty(input)){

            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("7",input,"Empty Input Line"));
            reverseSubstring();

        } else if (!InputUtils.checkBlank(input)){

            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("7",input,"Blank Input Line"));
            reverseSubstring();

        } else if (!InputUtils.checkEmpty(inputSubsequence)){

            ErrorMessages.getEmptySubsequenceLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("7",input,"Empty Subsequence Input Line"));
            reverseSubstring();

        } else if (!InputUtils.checkBlank(inputSubsequence)){

            ErrorMessages.getBlankSubsequenceLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("7",input,"Blank Subsequence Input Line"));
            reverseSubstring();

        } else if (!InputUtils.checkContainsSequence(input,inputSubsequence)){

            ErrorMessages.getStringNotContainsSubsequenceError();
            ErrorLog.errorLog.add(new Entry("7",input,"String doesn't contain this subsequence"));
            reverseSubstring();

        }
        stackOfTasks.add(7);
        Menu.runSubMenu();
    }

    public static void reverseSequencesWithCharacter() throws IOException, ParseException {
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();
        MenuMessages.getInputCharMessage();
        String inputChar = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input) &&
                InputUtils.checkEmpty(inputChar) && InputUtils.checkBlank(inputChar) &&
                InputUtils.checkInputCharRange(inputChar) &&
                InputUtils.checkContainsSymbol(input,inputChar.charAt(0))){

            String output = StringReverse.reverseSequencesWithCharacter(input, inputChar.charAt(0));
            MenuMessages.returnResult(input + ", " + inputChar,output);
            Log.log.add(new Entry("8",
                    input + ", " + inputChar,
                    output));

        } else if (!InputUtils.checkEmpty(input)){

            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("8",
                    input + ", " + inputChar ,
                    "Empty Input Line"));
            reverseSequencesWithCharacter();

        } else if (!InputUtils.checkBlank(input)){

            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("8",
                    input + ", " + inputChar ,
                    "Blank Input Line"));
            reverseSequencesWithCharacter();

        } else if (!InputUtils.checkEmpty(inputChar)){

            ErrorMessages.getEmptyCharLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("8",
                    input + ", " + inputChar ,
                    "Blank Character Input Line"));
            reverseSequencesWithCharacter();

        } else if (!InputUtils.checkBlank(inputChar)){

            ErrorMessages.getBlankCharLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("8",
                    input + ", " + inputChar ,
                    "Empty Character Input Line"));
            reverseSequencesWithCharacter();

        } else if (!InputUtils.checkInputCharRange(inputChar)){

            ErrorMessages.getCharacterFormatError();
            ErrorLog.errorLog.add(new Entry("8",
                    input + ", " + inputChar ,
                    "Input Line is too long for character"));
            reverseSequencesWithCharacter();

        } else if (!InputUtils.checkContainsSymbol(input,inputChar.charAt(0))){

            ErrorMessages.getStringNotContainsCharacterError();
            ErrorLog.errorLog.add(new Entry("8",
                    input + ", " + inputChar ,
                    "Input Line doesn't contain this character"));
            reverseSequencesWithCharacter();

        }
        stackOfTasks.add(8);
        Menu.runSubMenu();
    }

    public static void reverseSequencesAfterCharacter() throws IOException, ParseException{
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();
        MenuMessages.getInputCharMessage();
        String inputChar = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input) &&
                InputUtils.checkEmpty(inputChar) && InputUtils.checkBlank(inputChar) &&
                InputUtils.checkInputCharRange(inputChar) &&
                InputUtils.checkContainsSymbol(input,inputChar.charAt(0))){

            String output = StringReverse.reverseSequencesAfterCharacter(input, inputChar.charAt(0));
            MenuMessages.returnResult(input + ", " + inputChar,output);
            Log.log.add(new Entry("9",
                    input + ", " + inputChar,
                    output));

        } else if (!InputUtils.checkEmpty(input)){

            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("9",
                    input + ", " + inputChar ,
                    "Empty Input Line"));
            reverseSequencesAfterCharacter();

        } else if (!InputUtils.checkBlank(input)){

            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("9",
                    input + ", " + inputChar ,
                    "Blank Input Line"));
            reverseSequencesAfterCharacter();

        } else if (!InputUtils.checkEmpty(inputChar)){

            ErrorMessages.getEmptyCharLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("9",
                    input + ", " + inputChar ,
                    "Blank Character Input Line"));
            reverseSequencesAfterCharacter();

        } else if (!InputUtils.checkBlank(inputChar)){

            ErrorMessages.getBlankCharLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("9",
                    input + ", " + inputChar ,
                    "Empty Character Input Line"));
            reverseSequencesAfterCharacter();

        } else if (!InputUtils.checkInputCharRange(inputChar)){

            ErrorMessages.getCharacterFormatError();
            ErrorLog.errorLog.add(new Entry("9",
                    input + ", " + inputChar ,
                    "Input Line is too long for character"));
            reverseSequencesAfterCharacter();

        } else if (!InputUtils.checkContainsSymbol(input,inputChar.charAt(0))){

            ErrorMessages.getStringNotContainsCharacterError();
            ErrorLog.errorLog.add(new Entry("9",
                    input + ", " + inputChar ,
                    "Input Line doesn't contain this character"));
            reverseSequencesAfterCharacter();

        }
        stackOfTasks.add(9);
        Menu.runSubMenu();
    }

    public static void reverseSubstringsFromIndex() throws IOException, ParseException {
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();
        MenuMessages.getInputBeginIndexMessage();
        String inputIndex = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input) &&
                InputUtils.checkEmpty(inputIndex) && InputUtils.checkBlank(inputIndex) &&
                InputUtils.checkIfNumber(inputIndex) &&
                InputUtils.checkSubsequenceBeginRange(input,Integer.parseInt(inputIndex))){

            String output = StringReverse.reverseSubstringsFromIndex(input, Integer.parseInt(inputIndex));
            MenuMessages.returnResult(input + ", " + inputIndex, output);
            Log.log.add(new Entry("10",input + ", " + inputIndex,output));

        } else if (!InputUtils.checkEmpty(input)){

            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("10",input,"Empty Input String Line"));
            reverseSubstringsFromIndex();

        } else if (!InputUtils.checkBlank(input)) {

            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("10",input,"Blank Input String Line"));
            reverseSubstringsFromIndex();

        } else if (!InputUtils.checkEmpty(inputIndex)){

            ErrorMessages.getEmptyIndexLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("10",input,"Empty Input Index Line"));
            reverseSubstringsFromIndex();

        } else if (!InputUtils.checkBlank(inputIndex)){

            ErrorMessages.getBlankIndexLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("10",input,"Blank Input Index Line"));
            reverseSubstringsFromIndex();

        } else if (!InputUtils.checkIfNumber(inputIndex)){

            ErrorMessages.getNumberFormatError();
            ErrorLog.errorLog.add(new Entry("10",input,"Index Not a Number"));
            reverseSubstringsFromIndex();

        } else if (!InputUtils.checkSubsequenceBeginRange(input,Integer.parseInt(inputIndex))){

            ErrorMessages.getBeginIndexOutOfBoundsError();
            ErrorLog.errorLog.add(new Entry("10",input,"Index Out of Bounds"));
            reverseSubstringsFromIndex();
        }
        stackOfTasks.add(10);
        Menu.runSubMenu();
    }

    public static void reverseSubstringsByIndexes() throws IOException, ParseException {
        MenuMessages.getInputMessage();
        String input = InputUtils.returnLine();
        MenuMessages.getInputBeginIndexMessage();
        String inputBeginIndex = InputUtils.returnLine();
        MenuMessages.getInputEndIndexMessage();
        String inputEndIndex = InputUtils.returnLine();

        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input) &&

                InputUtils.checkEmpty(inputBeginIndex) && InputUtils.checkBlank(inputBeginIndex) &&
                InputUtils.checkIfNumber(inputBeginIndex) &&
                InputUtils.checkSubsequenceBeginRange(input,Integer.parseInt(inputBeginIndex)) &&

                InputUtils.checkEmpty(inputEndIndex) && InputUtils.checkBlank(inputEndIndex) &&
                InputUtils.checkIfNumber(inputEndIndex) &&
                InputUtils.checkSubsequenceBeginRange(input,Integer.parseInt(inputEndIndex)) &&

                InputUtils.checkSubsequenceRangeBetween(Integer.parseInt(inputBeginIndex),
                        Integer.parseInt(inputEndIndex))){

            String output = StringReverse.reverseSubstringsByIndexes(input,
                    Integer.parseInt(inputBeginIndex),
                    Integer.parseInt(inputEndIndex));
            MenuMessages.returnResult(input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    output);
            Log.log.add(new Entry("11",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,output));

        } else if (!InputUtils.checkEmpty(input)){

            ErrorMessages.getEmptyLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("11",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Empty Input String Line"));
            reverseSubstringsByIndexes();

        } else if (!InputUtils.checkBlank(input)) {

            ErrorMessages.getBlankLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("11",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Blank Input String Line"));
            reverseSubstringsByIndexes();

        } else if (!InputUtils.checkEmpty(inputBeginIndex)) {

            ErrorMessages.getEmptyBeginIndexLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("11",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Empty Input Index Line"));
            reverseSubstringsByIndexes();

        } else if (!InputUtils.checkBlank(inputBeginIndex)) {

            ErrorMessages.getBlankBeginIndexLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("11",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Blank Input Index Line"));
            reverseSubstringsByIndexes();

        } else if (!InputUtils.checkIfNumber(inputBeginIndex)) {

            ErrorMessages.getNumberFormatError();
            ErrorLog.errorLog.add(new Entry("11",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Begin Index is not a number"));
            reverseSubstringsByIndexes();

        } else if (!InputUtils.checkSubsequenceBeginRange(input, Integer.parseInt(inputBeginIndex))){

            ErrorMessages.getBeginIndexOutOfBoundsError();
            ErrorLog.errorLog.add(new Entry("11",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Begin Index is out of bounds"));
            reverseSubstringsByIndexes();

        } else if (!InputUtils.checkEmpty(inputEndIndex)) {

            ErrorMessages.getEmptyEndIndexLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("11",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Empty Input Index Line"));
            reverseSubstringsByIndexes();

        } else if (!InputUtils.checkBlank(inputEndIndex)) {

            ErrorMessages.getBlankEndIndexLineErrorMessage();
            ErrorLog.errorLog.add(new Entry("11",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Blank Input Index Line"));
            reverseSubstringsByIndexes();

        } else if (!InputUtils.checkIfNumber(inputEndIndex)) {

            ErrorMessages.getNumberFormatError();
            ErrorLog.errorLog.add(new Entry("11",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "End index is not a number"));
            reverseSubstringsByIndexes();

        } else if (!InputUtils.checkSubsequenceEndRange(input, Integer.parseInt(inputEndIndex))){

            ErrorMessages.getEndIndexOutOfBoundsError();
            ErrorLog.errorLog.add(new Entry("11",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "End Index is out of bounds"));
            reverseSubstringsByIndexes();

        }  else if (!InputUtils.checkSubsequenceRangeBetween(Integer.parseInt(inputBeginIndex),
                Integer.parseInt(inputEndIndex))){

            ErrorMessages.getBeginIndexGreaterThanEndIndexError();
            ErrorLog.errorLog.add(new Entry("11",
                    input + ", " + inputBeginIndex + ", " + inputEndIndex,
                    "Begin Index greater than End Index"));
            reverseSubstringsByIndexes();

        }
        stackOfTasks.add(11);
        Menu.runSubMenu();
    }
}
