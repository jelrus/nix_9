package ua.com.alevel.console.controller;

import ua.com.alevel.console.menu.Menu;
import ua.com.alevel.console.messages.ErrorMessages;
import ua.com.alevel.console.messages.MenuMessages;
import ua.com.alevel.console.utils.InputChecks;
import ua.com.alevel.console.utils.InputUtils;
import mathset.structures.MathSet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationController {

    public static final MathSet<MathSet<Number>> mathSets = new MathSet<>();

    public static int selectMathSet() throws IOException, ParseException {
        MenuMessages.selectMathSet();
        String select = InputUtils.returnLine();
        InputUtils.emptyMathSets(mathSets);
        if (InputChecks.checkIfStringNumber(select) && !mathSets.isEmpty()) {
            if (Integer.parseInt(select) < mathSets.size()) {
                return mathSets.indexOf(mathSets.get(Integer.parseInt(select)));
            } else {
                ErrorMessages.setIdNotFound();
                return selectMathSet();
            }
        } else {
            ErrorMessages.inputErrorMessage();
            return selectMathSet();
        }
    }

    public static void createDefaultMathSet() throws IOException, ParseException {
        Menu.subMenusTasks.add(1);
        MathSet<Number> defaultSet = new MathSet<>();
        mathSets.add(defaultSet);
        MenuMessages.mathSetUnlimitedSize(defaultSet, mathSets);
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void createLimitedSizeMathSet() throws IOException, ParseException {
        Menu.subMenusTasks.add(2);
        MenuMessages.selectCapacity();
        String capacityInput = InputUtils.returnLine();
        if (!capacityInput.isEmpty() && !capacityInput.isBlank() && InputChecks.checkIfStringNumber(capacityInput)) {
            int capacity = Integer.parseInt(capacityInput);
            MenuMessages.expandable();
            boolean expandable = InputUtils.expandableOrNot();
            MathSet<Number> limitedSizeSet = new MathSet<>(capacity, expandable);
            mathSets.add(limitedSizeSet);
            MenuMessages.mathSetLimitedSize(limitedSizeSet, mathSets, expandable);
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void createArrayBasedMathSet() throws IOException, ParseException {
        Menu.subMenusTasks.add(3);
        MenuMessages.arrayCapacity();
        String capacityInput = InputUtils.returnLine();
        if (!capacityInput.isEmpty() && !capacityInput.isBlank() && InputChecks.checkIfStringNumber(capacityInput)) {
            int capacity = Integer.parseInt(capacityInput);
            Number[] numbers = new Number[capacity];
            for (int i = 0; i < numbers.length; i++) {
                MenuMessages.arrayIndex(i);
                String numberString = InputUtils.returnLine();
                if (!numberString.isEmpty() && !numberString.isBlank() && InputChecks.checkIfNumber(numberString)) {
                    numbers[i] = new BigDecimal(numberString);
                } else {
                    ErrorMessages.inputErrorMessage();
                    MenuMessages.goBack();
                    Menu.returnToSubMenu();
                }
            }
            MathSet<Number> arrayBasedSet = new MathSet<>(numbers);
            mathSets.add(arrayBasedSet);
            MenuMessages.mathSetUnlimitedSize(arrayBasedSet, mathSets);
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void createVarArgsArraysBasedMathSet() throws IOException, ParseException {
        Menu.subMenusTasks.add(4);
        MenuMessages.numbersOfArrays();
        String inputString = InputUtils.returnLine();
        MathSet<Number> arrayBasedOnVarArgsSet = new MathSet<>();
        if (!inputString.isEmpty() && !inputString.isBlank() && InputChecks.checkIfNumber(inputString)) {
            int numbersOfArrays = Integer.parseInt(inputString);
            for (int i = 0; i < numbersOfArrays; i++) {
                MenuMessages.arrayCapacityInLoop(i);
                String capacityInput = InputUtils.returnLine();
                if (!capacityInput.isEmpty() && !capacityInput.isBlank() && InputChecks.checkIfStringNumber(capacityInput)) {
                    int capacity = Integer.parseInt(capacityInput);
                    Number[] numbers = new Number[capacity];
                    for (int j = 0; j < numbers.length; j++) {
                        MenuMessages.arrayIndex(j);
                        String numberString = InputUtils.returnLine();
                        if (!numberString.isEmpty() && !numberString.isBlank() && InputChecks.checkIfNumber(numberString)) {
                            numbers[j] = new BigDecimal(numberString);
                        } else {
                            ErrorMessages.inputErrorMessage();
                            MenuMessages.goBack();
                            Menu.returnToSubMenu();
                        }
                    }
                    arrayBasedOnVarArgsSet.add(numbers);
                } else {
                    ErrorMessages.inputErrorMessage();
                    MenuMessages.goBack();
                    Menu.returnToSubMenu();
                }
            }
            mathSets.add(arrayBasedOnVarArgsSet);
            MenuMessages.mathSetUnlimitedSize(arrayBasedOnVarArgsSet, mathSets);
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void createMathSetBasedOnMathSet() throws IOException, ParseException {
        Menu.subMenusTasks.add(5);
        MenuMessages.constructionWarning();
        int msId = selectMathSet();
        MathSet<Number> elementsBasedMathSet = new MathSet<>(mathSets.get(msId));
        mathSets.add(elementsBasedMathSet);
        MenuMessages.mathSetUnlimitedSize(elementsBasedMathSet, mathSets);
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void createMathSetBasedOnVarArgsOfMathSets() throws IOException, ParseException {
        Menu.subMenusTasks.add(6);
        MenuMessages.constructionWarning();
        MenuMessages.numbersOfMathSetsAdd();
        String setsNumberInput = InputUtils.returnLine();
        if (!setsNumberInput.isEmpty() && !setsNumberInput.isBlank() && InputChecks.checkIfNumber(setsNumberInput)) {
            int setsNumber = Integer.parseInt(setsNumberInput);
            MathSet<Number> mathSetBasedOnVarArgsOfMathSets = new MathSet<>();
            for (int i = 0; i < setsNumber; i++) {
                int msId = selectMathSet();
                MathSet<Number> getMathSet = mathSets.get(msId);
                mathSetBasedOnVarArgsOfMathSets.copy(getMathSet);
            }
            mathSets.add(mathSetBasedOnVarArgsOfMathSets);
            MenuMessages.mathSetUnlimitedSize(mathSetBasedOnVarArgsOfMathSets, mathSets);
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void addElement() throws IOException, ParseException {
        Menu.subMenusTasks.add(7);
        int index = selectMathSet();
        MenuMessages.addValue();
        String inputLine = InputUtils.returnLine();
        if (!inputLine.isEmpty() && !inputLine.isBlank() && InputChecks.checkIfNumber(inputLine)) {
            Number number = new BigDecimal(inputLine);
            if (mathSets.get(index).getCapacity() != mathSets.get(index).size()) {
                mathSets.get(index).add(number);
            } else {
                ErrorMessages.mathSetIsFull();
                MenuMessages.goBack();
                Menu.returnToSubMenu();
            }
            MenuMessages.mathSetUnlimitedSize(mathSets.get(index), mathSets);
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void addElements() throws IOException, ParseException {
        Menu.subMenusTasks.add(8);
        int index = selectMathSet();
        MenuMessages.addValues();
        String inputLine = InputUtils.returnLine();
        if (!inputLine.isEmpty() && !inputLine.isBlank()) {
            Pattern numberPattern = Pattern.compile("(-?\\d+(\\.\\d+)?)", Pattern.MULTILINE);
            Matcher numberMatcher = numberPattern.matcher(inputLine);
            while (numberMatcher.find()) {
                if (mathSets.get(index).getCapacity() != mathSets.get(index).size()) {
                    mathSets.get(index).add(new BigDecimal(numberMatcher.group()));
                } else {
                    ErrorMessages.notAllElementsAdded();
                    MenuMessages.mathSetUnlimitedSize(mathSets.get(index), mathSets);
                    MenuMessages.goBack();
                    Menu.returnToSubMenu();
                }
            }
            MenuMessages.mathSetUnlimitedSize(mathSets.get(index), mathSets);
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void setElement() throws IOException, ParseException {
        Menu.subMenusTasks.add(9);
        int msIndex = selectMathSet();
        MenuMessages.elementIndex();
        String indexInput = InputUtils.returnLine();
        if (!indexInput.isEmpty() && !indexInput.isBlank() && InputChecks.checkIfStringNumber(indexInput)) {
            int elementIndex = Integer.parseInt(indexInput);
            if (elementIndex >= 0 && elementIndex < mathSets.get(msIndex).size()) {
                MenuMessages.addValue();
                String inputLine = InputUtils.returnLine();
                if (!inputLine.isEmpty() && !inputLine.isBlank() && InputChecks.checkIfNumber(inputLine)) {
                    Number number = new BigDecimal(inputLine);
                    mathSets.get(msIndex).set(elementIndex, number);
                    MenuMessages.mathSetUnlimitedSize(mathSets.get(msIndex), mathSets);
                } else {
                    ErrorMessages.inputErrorMessage();
                    MenuMessages.goBack();
                    Menu.returnToSubMenu();
                }
            } else {
                ErrorMessages.indexOutOfBounds();
                MenuMessages.goBack();
                Menu.returnToSubMenu();
            }
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void getElementByIndex() throws IOException, ParseException {
        Menu.subMenusTasks.add(10);
        int index = selectMathSet();
        MenuMessages.elementIndex();
        String indexInput = InputUtils.returnLine();
        if (!indexInput.isEmpty() && !indexInput.isBlank() && InputChecks.checkIfStringNumber(indexInput)) {
            int elementIndex = Integer.parseInt(indexInput);
            if (elementIndex >= 0 && elementIndex < mathSets.get(index).size()) {
                MenuMessages.getElement(index, elementIndex, mathSets.get(index).get(elementIndex));
            } else {
                ErrorMessages.indexOutOfBounds();
                MenuMessages.goBack();
                Menu.returnToSubMenu();
            }
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void clear() throws IOException, ParseException {
        Menu.subMenusTasks.add(11);
        int index = selectMathSet();
        mathSets.get(index).clear();
        MenuMessages.mathSetUnlimitedSize(mathSets.get(index), mathSets);
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void clearAllFromArray() throws IOException, ParseException {
        Menu.subMenusTasks.add(12);
        int msIndex = selectMathSet();
        MenuMessages.arrayCapacity();
        String arrCapInput = InputUtils.returnLine();
        if (!arrCapInput.isEmpty() && !arrCapInput.isBlank() && InputChecks.checkIfStringNumber(arrCapInput)) {
            int arrayCapacity = Integer.parseInt(arrCapInput);
            Number[] numbers = new Number[arrayCapacity];
            for (int i = 0; i < numbers.length; i++) {
                MenuMessages.arrayIndex(i);
                String numberInput = InputUtils.returnLine();
                if (!numberInput.isEmpty() && !numberInput.isBlank() && InputChecks.checkIfNumber(numberInput)) {
                    numbers[i] = new BigDecimal(numberInput);
                } else {
                    ErrorMessages.inputErrorMessage();
                    MenuMessages.goBack();
                    Menu.returnToSubMenu();
                }
            }
            mathSets.get(msIndex).clear(numbers);
            MenuMessages.mathSetUnlimitedSize(mathSets.get(msIndex), mathSets);
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void toArray() throws IOException, ParseException {
        Menu.subMenusTasks.add(13);
        int index = selectMathSet();
        MenuMessages.toArrayResult(mathSets.get(index));
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void toArrayBetweenIndexes() throws IOException, ParseException {
        Menu.subMenusTasks.add(14);
        int index = selectMathSet();
        MenuMessages.getFirstIndexArray();
        String firstIndexInput = InputUtils.returnLine();
        MenuMessages.getLastIndexArray();
        String secondIndexInput = InputUtils.returnLine();
        if (!firstIndexInput.isEmpty() && !firstIndexInput.isBlank() && InputChecks.checkIfStringNumber(firstIndexInput)
                && !secondIndexInput.isEmpty() && !secondIndexInput.isBlank() && InputChecks.checkIfStringNumber(secondIndexInput)) {
            MenuMessages.getFirstIndexArray();
            int firstIndex = Integer.parseInt(firstIndexInput);
            int secondIndex = Integer.parseInt(secondIndexInput);
            if (firstIndex >= 0 && secondIndex < mathSets.get(index).size() && firstIndex <= secondIndex) {
                mathSets.get(index).toArray(firstIndex, secondIndex);
                MenuMessages.toArrayBetweenIndexesResult(mathSets.get(index), firstIndex, secondIndex);
            } else {
                ErrorMessages.inputErrorMessage();
                MenuMessages.goBack();
                Menu.returnToSubMenu();
            }
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void getMin() throws IOException, ParseException {
        Menu.subMenusTasks.add(15);
        int index = selectMathSet();
        MenuMessages.getMin(mathSets.get(index).getMin());
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void getMax() throws IOException, ParseException {
        Menu.subMenusTasks.add(16);
        int index = selectMathSet();
        MenuMessages.getMax(mathSets.get(index).getMax());
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void getAverage() throws IOException, ParseException {
        Menu.subMenusTasks.add(17);
        int index = selectMathSet();
        MenuMessages.getAverage(mathSets.get(index).getAverage());
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void getMedian() throws IOException, ParseException {
        Menu.subMenusTasks.add(18);
        int index = selectMathSet();
        MenuMessages.getMedian(mathSets.get(index).getMedian());
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void sortAsc() throws IOException, ParseException {
        Menu.subMenusTasks.add(19);
        int index = selectMathSet();
        mathSets.get(index).sortAsc();
        MenuMessages.mathSetUnlimitedSize(mathSets.get(index), mathSets);
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void sortAscBetweenIndexes() throws IOException, ParseException {
        Menu.subMenusTasks.add(20);
        int index = selectMathSet();
        MenuMessages.getFirstIndexSort();
        String firstIndexInput = InputUtils.returnLine();
        MenuMessages.getLastIndexSort();
        String lastIndexInput = InputUtils.returnLine();
        if (!firstIndexInput.isEmpty() && !firstIndexInput.isBlank() && InputChecks.checkIfStringNumber(firstIndexInput)
                && !lastIndexInput.isEmpty() && !lastIndexInput.isBlank() && InputChecks.checkIfStringNumber(lastIndexInput)) {
            int firstIndex = Integer.parseInt(firstIndexInput);
            int lastIndex = Integer.parseInt(lastIndexInput);
            if (firstIndex >= 0 && lastIndex < mathSets.get(index).size() && lastIndex >= firstIndex) {
                mathSets.get(index).sortAsc(firstIndex, lastIndex);
                MenuMessages.mathSetUnlimitedSize(mathSets.get(index), mathSets);
            } else {
                ErrorMessages.inputErrorMessage();
                MenuMessages.goBack();
                Menu.returnToSubMenu();
            }
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void sortAscFromElement() throws IOException, ParseException {
        Menu.subMenusTasks.add(21);
        int index = selectMathSet();
        MenuMessages.getElementStartPoint();
        String sortPointInput = InputUtils.returnLine();
        if (!sortPointInput.isEmpty() && !sortPointInput.isBlank() && InputChecks.checkIfNumber(sortPointInput)) {
            Number sortPoint = new BigDecimal(sortPointInput);
            mathSets.get(index).sortAsc(sortPoint);
            MenuMessages.mathSetUnlimitedSize(mathSets.get(index), mathSets);
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void sortDesc() throws IOException, ParseException {
        Menu.subMenusTasks.add(22);
        int index = selectMathSet();
        mathSets.get(index).sortDesc();
        MenuMessages.mathSetUnlimitedSize(mathSets.get(index), mathSets);
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void sortDescBetweenIndexes() throws IOException, ParseException {
        Menu.subMenusTasks.add(23);
        int index = selectMathSet();
        MenuMessages.getFirstIndexSort();
        String firstIndexInput = InputUtils.returnLine();
        MenuMessages.getLastIndexSort();
        String lastIndexInput = InputUtils.returnLine();
        if (!firstIndexInput.isEmpty() && !firstIndexInput.isBlank() && InputChecks.checkIfStringNumber(firstIndexInput)
                && !lastIndexInput.isEmpty() && !lastIndexInput.isBlank() && InputChecks.checkIfStringNumber(lastIndexInput)) {
            int firstIndex = Integer.parseInt(firstIndexInput);
            int lastIndex = Integer.parseInt(lastIndexInput);
            if (firstIndex >= 0 && lastIndex < mathSets.get(index).size() && lastIndex >= firstIndex) {
                mathSets.get(index).sortDesc(firstIndex, lastIndex);
                MenuMessages.mathSetUnlimitedSize(mathSets.get(index), mathSets);
            } else {
                ErrorMessages.inputErrorMessage();
                MenuMessages.goBack();
                Menu.returnToSubMenu();
            }
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void sortDescFromElement() throws IOException, ParseException {
        Menu.subMenusTasks.add(24);
        int index = selectMathSet();
        MenuMessages.getElementStartPoint();
        String sortPointInput = InputUtils.returnLine();
        if (!sortPointInput.isEmpty() && !sortPointInput.isBlank() && InputChecks.checkIfNumber(sortPointInput)) {
            Number sortPoint = new BigDecimal(sortPointInput);
            mathSets.get(index).sortDesc(sortPoint);
            MenuMessages.mathSetUnlimitedSize(mathSets.get(index), mathSets);
        } else {
            ErrorMessages.inputErrorMessage();
            sortDescFromElement();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void joinWithSingleMathSet() throws IOException, ParseException {
        Menu.subMenusTasks.add(25);
        int indexFirst = selectMathSet();
        int indexSecond = selectMathSet();
        if ((mathSets.get(indexFirst).getCapacity() - mathSets.get(indexFirst).size()) >= mathSets.get(indexSecond).size()) {
            mathSets.get(indexFirst).join(mathSets.get(indexSecond));
            MenuMessages.mathSetUnlimitedSize(mathSets.get(indexFirst), mathSets);
        } else {
            ErrorMessages.mathSetDoesNotFit();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void joinWithMultipleMathSets() throws IOException, ParseException {
        Menu.subMenusTasks.add(26);
        int indexFirst = selectMathSet();
        MenuMessages.numbersOfMathSetsJoin();
        String joinNumberInput = InputUtils.returnLine();
        if (!joinNumberInput.isEmpty() && !joinNumberInput.isBlank() && InputChecks.checkIfStringNumber(joinNumberInput)) {
            int interNumber = Integer.parseInt(joinNumberInput);
            for (int i = 0; i < interNumber; i++) {
                int indexNext = selectMathSet();
                if ((mathSets.get(indexFirst).getCapacity() - mathSets.get(indexFirst).size()) >= mathSets.get(indexNext).size()) {
                    mathSets.get(indexFirst).join(mathSets.get(indexNext));
                } else {
                    ErrorMessages.mathSetDoesNotFit();
                    MenuMessages.goBack();
                    Menu.returnToSubMenu();
                }
            }
            MenuMessages.mathSetUnlimitedSize(mathSets.get(indexFirst), mathSets);
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void intersectionWithSingleMathSet() throws IOException, ParseException {
        Menu.subMenusTasks.add(27);
        int indexFirst = selectMathSet();
        int indexSecond = selectMathSet();
        mathSets.get(indexFirst).intersection(mathSets.get(indexSecond));
        MenuMessages.mathSetUnlimitedSize(mathSets.get(indexFirst), mathSets);
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void intersectionWithMultipleMathSets() throws IOException, ParseException {
        Menu.subMenusTasks.add(28);
        int indexFirst = selectMathSet();
        MenuMessages.numbersOfMathSetsIntersection();
        String interNumberInput = InputUtils.returnLine();
        if (!interNumberInput.isEmpty() && !interNumberInput.isBlank() && InputChecks.checkIfStringNumber(interNumberInput)) {
            int interNumber = Integer.parseInt(interNumberInput);
            for (int i = 0; i < interNumber; i++) {
                int indexNext = selectMathSet();
                mathSets.get(indexFirst).intersection(mathSets.get(indexNext));
            }
            MenuMessages.mathSetUnlimitedSize(mathSets.get(indexFirst), mathSets);
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }

    public static void cutElements() throws IOException, ParseException {
        Menu.subMenusTasks.add(29);
        int index = selectMathSet();
        MenuMessages.getFirstIndexCut();
        String indexFirstCutInput = InputUtils.returnLine();
        String indexSecondCutInput = InputUtils.returnLine();
        MenuMessages.getLastIndexCut();
        if (!indexFirstCutInput.isEmpty() && !indexFirstCutInput.isBlank() && InputChecks.checkIfStringNumber(indexFirstCutInput)
                && !indexSecondCutInput.isEmpty() && !indexSecondCutInput.isBlank() && InputChecks.checkIfStringNumber(indexSecondCutInput)) {
            int indexFirstCut = Integer.parseInt(indexFirstCutInput);
            int indexSecondCut = Integer.parseInt(indexSecondCutInput);
            if (indexFirstCut >= 0 && indexSecondCut < mathSets.get(index).size() && indexFirstCut <= indexSecondCut) {
                MenuMessages.cutResult(mathSets.get(index).cut(indexFirstCut, indexSecondCut));
            } else {
                ErrorMessages.inputErrorMessage();
                MenuMessages.goBack();
                Menu.returnToSubMenu();
            }
        } else {
            ErrorMessages.inputErrorMessage();
            MenuMessages.goBack();
            Menu.returnToSubMenu();
        }
        MenuMessages.goBack();
        Menu.returnToSubMenu();
    }
}