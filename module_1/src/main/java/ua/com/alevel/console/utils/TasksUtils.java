package ua.com.alevel.console.utils;

import ua.com.alevel.console.menus.Menu;
import ua.com.alevel.console.messages.ErrorMessages;
import ua.com.alevel.console.messages.MenuMessages;
import ua.com.alevel.levels.first.horse.ChessBoard;
import ua.com.alevel.levels.first.horse.ChessField;
import ua.com.alevel.levels.first.horse.HorseMove;
import ua.com.alevel.levels.first.numbers.UniqueDigits;
import ua.com.alevel.levels.first.triangle.Point;
import ua.com.alevel.levels.first.triangle.Triangle;
import ua.com.alevel.levels.second.brackets.BracketsEqualityDetector;
import ua.com.alevel.levels.second.tree.BinaryTree;
import ua.com.alevel.levels.third.life.ConwaysGameOfLife;
import ua.com.alevel.levels.third.life.PresetFigures;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

public class TasksUtils {

    public static LinkedList<Integer> stackOfTasks = new LinkedList<>();

    public static void countDigits() throws IOException, ParseException {
        MenuMessages.returnInputText();
        String digits = InputUtils.returnLine();
        if (InputUtils.checkEmpty(digits) && InputUtils.checkBlank(digits)) {
            int result = UniqueDigits.countUniqueDigits(digits);
            MenuMessages.returnResult(digits, Integer.toString(result));
        } else if (!InputUtils.checkEmpty(digits)) {
            ErrorMessages.getEmptyLineErrorMessage();
            countDigits();
        } else if (!InputUtils.checkBlank(digits)) {
            ErrorMessages.getBlankLineErrorMessage();
            countDigits();
        }
        stackOfTasks.add(1);
        Menu.runSubMenu();
    }

    public static void startHorseMoving() throws IOException, ParseException {
        MenuMessages.returnHorseInputText();
        String basePosition = InputUtils.returnLine();
        if (InputUtils.checkEmpty(basePosition) && InputUtils.checkBlank(basePosition)
                && InputUtils.checkRange(basePosition)) {
            ChessField chessField = InputUtils.convertLineToMove(basePosition);
            if (HorseMove.trueMoves.isEmpty() && HorseMove.inRange(chessField)) {
                MenuMessages.resultHeader();
                HorseMove.filterMoves(HorseMove.calculateMoves(chessField));
                MenuMessages.showAvailableMoves(chessField);
                MenuMessages.moveResult(basePosition, HorseMove.trueMoves.toString());
            } else if (HorseMove.trueMoves.contains(chessField)) {
                HorseMove.trueMoves.clear();
                MenuMessages.resultHeader();
                HorseMove.filterMoves(HorseMove.calculateMoves(chessField));
                MenuMessages.showAvailableMoves(chessField);
                MenuMessages.moveResult(basePosition, HorseMove.trueMoves.toString());
            } else if (HorseMove.trueMoves.isEmpty()) {
                ErrorMessages.getIncorrectPositionOrNoMoves();
            } else {
                ErrorMessages.getNotAvailableMoveErrorMessage();
                MenuMessages.listOfAvailableMoves();
            }
        } else if (!InputUtils.checkEmpty(basePosition)) {
            ErrorMessages.getEmptyLineErrorMessage();
            startHorseMoving();
        } else if (!InputUtils.checkBlank(basePosition)) {
            ErrorMessages.getBlankLineErrorMessage();
            startHorseMoving();
        } else if (!InputUtils.checkRange(basePosition)) {
            ErrorMessages.getNotInRangeErrorMessage();
            startHorseMoving();
        }
        stackOfTasks.add(2);
        Menu.runSubMenu();
    }

    public static void calculateTriangleSquare() throws IOException, ParseException {
        MenuMessages.returnInputAXText();
        String axLine = InputUtils.returnLine();
        MenuMessages.returnInputAYText();
        String ayLine = InputUtils.returnLine();
        MenuMessages.returnInputBXText();
        String bxLine = InputUtils.returnLine();
        MenuMessages.returnInputBYText();
        String byLine = InputUtils.returnLine();
        MenuMessages.returnInputCXText();
        String cxLine = InputUtils.returnLine();
        MenuMessages.returnInputCYText();
        String cyLine = InputUtils.returnLine();
        if (InputUtils.checkEmpty(axLine) && InputUtils.checkBlank(axLine) && InputUtils.checkIfNumber(axLine) &&
                InputUtils.checkEmpty(ayLine) && InputUtils.checkBlank(ayLine) && InputUtils.checkIfNumber(ayLine) &&
                InputUtils.checkEmpty(bxLine) && InputUtils.checkBlank(bxLine) && InputUtils.checkIfNumber(bxLine) &&
                InputUtils.checkEmpty(byLine) && InputUtils.checkBlank(byLine) && InputUtils.checkIfNumber(byLine) &&
                InputUtils.checkEmpty(cxLine) && InputUtils.checkBlank(cxLine) && InputUtils.checkIfNumber(cxLine) &&
                InputUtils.checkEmpty(cyLine) && InputUtils.checkBlank(cyLine) && InputUtils.checkIfNumber(cyLine)) {
            Point pointA = new Point(Integer.parseInt(axLine), Integer.parseInt(ayLine));
            Point pointB = new Point(Integer.parseInt(bxLine), Integer.parseInt(byLine));
            Point pointC = new Point(Integer.parseInt(cxLine), Integer.parseInt(cyLine));
            double square = Triangle.calculateSquare(pointA, pointB, pointC);
            MenuMessages.returnSquareResult(axLine, ayLine, bxLine, byLine, cxLine, cyLine, String.valueOf(square));
        } else if (!InputUtils.checkEmpty(axLine)) {
            ErrorMessages.getEmptyLineErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkBlank(axLine)) {
            ErrorMessages.getBlankLineErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkIfNumber(axLine)) {
            ErrorMessages.getNotANumberErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkEmpty(ayLine)) {
            ErrorMessages.getEmptyLineErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkBlank(ayLine)) {
            ErrorMessages.getBlankLineErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkIfNumber(ayLine)) {
            ErrorMessages.getNotANumberErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkEmpty(bxLine)) {
            ErrorMessages.getEmptyLineErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkBlank(bxLine)) {
            ErrorMessages.getBlankLineErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkIfNumber(bxLine)) {
            ErrorMessages.getNotANumberErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkEmpty(byLine)) {
            ErrorMessages.getEmptyLineErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkBlank(byLine)) {
            ErrorMessages.getBlankLineErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkIfNumber(byLine)) {
            ErrorMessages.getNotANumberErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkEmpty(cxLine)) {
            ErrorMessages.getEmptyLineErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkBlank(cxLine)) {
            ErrorMessages.getBlankLineErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkIfNumber(cxLine)) {
            ErrorMessages.getNotANumberErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkEmpty(cyLine)) {
            ErrorMessages.getEmptyLineErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkBlank(cyLine)) {
            ErrorMessages.getBlankLineErrorMessage();
            calculateTriangleSquare();
        } else if (!InputUtils.checkIfNumber(cyLine)) {
            ErrorMessages.getNotANumberErrorMessage();
            calculateTriangleSquare();
        }
        stackOfTasks.add(3);
        Menu.runSubMenu();
    }

    public static void bracketsEquality() throws IOException, ParseException {
        MenuMessages.returnInputText();
        String checkedLine = InputUtils.returnLine();
        boolean bracketsEqual = BracketsEqualityDetector.bracketsEquals(checkedLine);
        if (bracketsEqual) {
            MenuMessages.returnEqualityResultTrue(checkedLine, String.valueOf(true));
        } else {
            MenuMessages.returnEqualityResultFalse(checkedLine, String.valueOf(false));
        }
        stackOfTasks.add(4);
        Menu.runSubMenu();
    }

    public static void maxDepthOfBinaryTree() throws IOException, ParseException {
        MenuMessages.returnInputTextForBinaryTree();
        String numbersInput = InputUtils.returnLine();
        ArrayList<Integer> parsedIntegers = InputUtils.parseIntegersFromInput(numbersInput);
        if (InputUtils.checkEmpty(numbersInput) && InputUtils.checkBlank(numbersInput)) {
            BinaryTree binaryTree = new BinaryTree();
            for (Integer parsedInteger : parsedIntegers) {
                binaryTree.add(parsedInteger);
            }
            int maxDepth = binaryTree.maxDepth(binaryTree.getRoot());
            MenuMessages.returnBinaryTreeMaxDepthResult(numbersInput, parsedIntegers, binaryTree.toString(),
                    String.valueOf(maxDepth));
        } else if (!InputUtils.checkEmpty(numbersInput)) {
            ErrorMessages.getEmptyLineErrorMessage();
            maxDepthOfBinaryTree();
        } else if (!InputUtils.checkBlank(numbersInput)) {
            ErrorMessages.getBlankLineErrorMessage();
            maxDepthOfBinaryTree();
        }
        stackOfTasks.add(5);
        Menu.runSubMenu();
    }

    public static void gameOfLife() throws IOException, ParseException {
        MenuMessages.returnHeightInputMessage();
        String heightInput = InputUtils.returnLine();
        MenuMessages.returnWidthInputMessage();
        String widthInput = InputUtils.returnLine();
        if (InputUtils.checkEmpty(heightInput) && InputUtils.checkBlank(heightInput) &&
                InputUtils.checkIfNumber(heightInput) && InputUtils.checkEmpty(widthInput) &&
                InputUtils.checkBlank(widthInput) && InputUtils.checkIfPositiveInteger(widthInput)) {
            int height = Integer.parseInt(heightInput);
            int width = Integer.parseInt(widthInput);
            ConwaysGameOfLife conwaysGameOfLife = new ConwaysGameOfLife(height, width);
            MenuMessages.returnBaseGrid(conwaysGameOfLife.toString());
            setPointsAddedAmount(conwaysGameOfLife);
            setPointsRemovedAmount(conwaysGameOfLife);
            setTurnsSection(conwaysGameOfLife);
        } else if (!InputUtils.checkEmpty(heightInput)) {
            ErrorMessages.getEmptyLineErrorMessage();
            gameOfLife();
        } else if (!InputUtils.checkBlank(heightInput)) {
            ErrorMessages.getBlankLineErrorMessage();
            gameOfLife();
        } else if (!InputUtils.checkIfPositiveInteger(heightInput)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            gameOfLife();
        } else if (!InputUtils.checkEmpty(widthInput)) {
            ErrorMessages.getEmptyLineErrorMessage();
            gameOfLife();
        } else if (!InputUtils.checkBlank(widthInput)) {
            ErrorMessages.getBlankLineErrorMessage();
            gameOfLife();
        } else if (!InputUtils.checkIfPositiveInteger(widthInput)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            gameOfLife();
        }
        stackOfTasks.add(6);
        Menu.runSubMenu();
    }

    public static void setPointsAddedAmount(ConwaysGameOfLife conwaysGameOfLife) throws IOException {
        MenuMessages.setPointMessage();
        String pointsSetter = InputUtils.returnLine();
        if (InputUtils.checkEmpty(pointsSetter) && InputUtils.checkBlank(pointsSetter) &&
                InputUtils.checkIfPositiveInteger(pointsSetter)) {
            int pointsAmount = Integer.parseInt(pointsSetter);
            for (int i = 0; i < pointsAmount; i++) {
                addPointSection(conwaysGameOfLife);
                MenuMessages.pointAdded(conwaysGameOfLife.toString());
            }
        } else if (!InputUtils.checkEmpty(pointsSetter)) {
            ErrorMessages.getEmptyLineErrorMessage();
            setPointsAddedAmount(conwaysGameOfLife);
        } else if (!InputUtils.checkBlank(pointsSetter)) {
            ErrorMessages.getBlankLineErrorMessage();
            setPointsAddedAmount(conwaysGameOfLife);
        } else if (!InputUtils.checkIfPositiveInteger(pointsSetter)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            setPointsAddedAmount(conwaysGameOfLife);
        }
    }

    public static void setPointsRemovedAmount(ConwaysGameOfLife conwaysGameOfLife) throws IOException {
        MenuMessages.removePointMessage();
        String pointsSetter = InputUtils.returnLine();
        if (InputUtils.checkEmpty(pointsSetter) && InputUtils.checkBlank(pointsSetter) &&
                InputUtils.checkIfPositiveInteger(pointsSetter)) {
            int pointsAmount = Integer.parseInt(pointsSetter);
            for (int i = 0; i < pointsAmount; i++) {
                removePointSection(conwaysGameOfLife);
                MenuMessages.pointRemoved(conwaysGameOfLife.toString());
            }
        } else if (!InputUtils.checkEmpty(pointsSetter)) {
            ErrorMessages.getEmptyLineErrorMessage();
            setPointsRemovedAmount(conwaysGameOfLife);
        } else if (!InputUtils.checkBlank(pointsSetter)) {
            ErrorMessages.getBlankLineErrorMessage();
            setPointsRemovedAmount(conwaysGameOfLife);
        } else if (!InputUtils.checkIfPositiveInteger(pointsSetter)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            setPointsRemovedAmount(conwaysGameOfLife);
        }
    }

    public static void addPointSection(ConwaysGameOfLife conwaysGameOfLife) throws IOException {
        MenuMessages.returnVerticalPosMessage();
        String vertPos = InputUtils.returnLine();
        MenuMessages.returnHorizontalPosMessage();
        String horPos = InputUtils.returnLine();
        if (InputUtils.checkEmpty(vertPos) && InputUtils.checkBlank(vertPos) && InputUtils.checkIfPositiveInteger(vertPos) &&
                InputUtils.checkEmpty(horPos) && InputUtils.checkBlank(horPos) && InputUtils.checkIfPositiveInteger(horPos)) {
            if (InputUtils.checkIfExists(conwaysGameOfLife.getUniverse().length, conwaysGameOfLife.getUniverse()[0].length,
                    Integer.parseInt(vertPos), Integer.parseInt(horPos))) {
                int verticalPos = Integer.parseInt(vertPos);
                int horizontalPos = Integer.parseInt(horPos);
                conwaysGameOfLife.addPoint(verticalPos, horizontalPos);
            } else if (!InputUtils.checkIfExists(conwaysGameOfLife.getUniverse().length, conwaysGameOfLife.getUniverse()[0].length,
                    Integer.parseInt(vertPos), Integer.parseInt(horPos))) {
                ErrorMessages.getPointIsOutOfBoundsErrorMessage();
                addPointSection(conwaysGameOfLife);
            }
        } else if (!InputUtils.checkEmpty(vertPos)) {
            ErrorMessages.getEmptyLineErrorMessage();
            removePointSection(conwaysGameOfLife);
        } else if (!InputUtils.checkBlank(vertPos)) {
            ErrorMessages.getBlankLineErrorMessage();
            removePointSection(conwaysGameOfLife);
        } else if (!InputUtils.checkIfPositiveInteger(vertPos)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            removePointSection(conwaysGameOfLife);
        } else if (!InputUtils.checkEmpty(horPos)) {
            ErrorMessages.getEmptyLineErrorMessage();
            removePointSection(conwaysGameOfLife);
        } else if (!InputUtils.checkBlank(horPos)) {
            ErrorMessages.getBlankLineErrorMessage();
            removePointSection(conwaysGameOfLife);
        } else if (!InputUtils.checkIfPositiveInteger(horPos)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            removePointSection(conwaysGameOfLife);
        }
    }

    public static void removePointSection(ConwaysGameOfLife conwaysGameOfLife) throws IOException {
        MenuMessages.returnVerticalPosMessage();
        String vertPos = InputUtils.returnLine();
        MenuMessages.returnHorizontalPosMessage();
        String horPos = InputUtils.returnLine();
        if (InputUtils.checkEmpty(vertPos) && InputUtils.checkBlank(vertPos) && InputUtils.checkIfPositiveInteger(vertPos) &&
                InputUtils.checkEmpty(horPos) && InputUtils.checkBlank(horPos) && InputUtils.checkIfPositiveInteger(horPos)) {
            if (InputUtils.checkIfExists(conwaysGameOfLife.getUniverse().length,
                    conwaysGameOfLife.getUniverse()[0].length,
                    Integer.parseInt(vertPos), Integer.parseInt(horPos))) {
                int verticalPos = Integer.parseInt(vertPos);
                int horizontalPos = Integer.parseInt(horPos);
                conwaysGameOfLife.deletePoint(verticalPos, horizontalPos);
            } else if (!InputUtils.checkIfExists(conwaysGameOfLife.getUniverse().length,
                    conwaysGameOfLife.getUniverse()[0].length,
                    Integer.parseInt(vertPos), Integer.parseInt(horPos))) {
                ErrorMessages.getPointIsOutOfBoundsErrorMessage();
                removePointSection(conwaysGameOfLife);
            }
        } else if (!InputUtils.checkEmpty(vertPos)) {
            ErrorMessages.getEmptyLineErrorMessage();
            removePointSection(conwaysGameOfLife);
        } else if (!InputUtils.checkBlank(vertPos)) {
            ErrorMessages.getBlankLineErrorMessage();
            removePointSection(conwaysGameOfLife);
        } else if (!InputUtils.checkIfPositiveInteger(vertPos)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            removePointSection(conwaysGameOfLife);
        } else if (!InputUtils.checkEmpty(horPos)) {
            ErrorMessages.getEmptyLineErrorMessage();
            removePointSection(conwaysGameOfLife);
        } else if (!InputUtils.checkBlank(horPos)) {
            ErrorMessages.getBlankLineErrorMessage();
            removePointSection(conwaysGameOfLife);
        } else if (!InputUtils.checkIfPositiveInteger(horPos)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            removePointSection(conwaysGameOfLife);
        }
    }

    public static void setTurnsSection(ConwaysGameOfLife conwaysGameOfLife) throws IOException {
        MenuMessages.returnGenerationInputMessage();
        String input = InputUtils.returnLine();
        if (InputUtils.checkEmpty(input) && InputUtils.checkBlank(input) && InputUtils.checkIfPositiveInteger(input)) {
            MenuMessages.returnBaseGameGrid(conwaysGameOfLife.toString());
            int generation = Integer.parseInt(input);
            for (int i = 1; i <= generation; i++) {
                MenuMessages.returnNextGensGameGrid(String.valueOf(i), conwaysGameOfLife.nextGen().toString());
            }
        } else if (!InputUtils.checkEmpty(input)) {
            ErrorMessages.getEmptyLineErrorMessage();
            setTurnsSection(conwaysGameOfLife);
        } else if (!InputUtils.checkBlank(input)) {
            ErrorMessages.getBlankLineErrorMessage();
            setTurnsSection(conwaysGameOfLife);
        } else if (!InputUtils.checkIfPositiveInteger(input)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            setTurnsSection(conwaysGameOfLife);
        }
    }

    public static void gameOfLifeWithPresetFigures() throws IOException, ParseException {
        ConwaysGameOfLife conwaysGameOfLife = new ConwaysGameOfLife(20, 20);
        addFigureSection(conwaysGameOfLife);
        stackOfTasks.add(7);
        Menu.runSubMenu();
    }

    public static boolean formOfLifeMenu(ConwaysGameOfLife conwaysGameOfLife) throws IOException {
        MenuMessages.returnVerticalPosMessage();
        String horPos = InputUtils.returnLine();
        MenuMessages.returnHorizontalPosMessage();
        String vertPos = InputUtils.returnLine();
        MenuMessages.chooseFigureTextMenu();
        String formOfLifeOption = InputUtils.returnLine();
        boolean added = false;
        if (InputUtils.checkEmpty(vertPos) && InputUtils.checkBlank(vertPos) && InputUtils.checkIfPositiveInteger(vertPos) &&
                InputUtils.checkEmpty(horPos) && InputUtils.checkBlank(horPos) && InputUtils.checkIfPositiveInteger(horPos)) {
            switch (formOfLifeOption) {
                case "1" -> added = PresetFigures.addBlock(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                case "2" -> added = PresetFigures.addBeehive(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                case "3" -> added = PresetFigures.addLoaf(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                case "4" -> added = PresetFigures.addBoat(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                case "5" -> added = PresetFigures.addTub(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                case "6" -> added = PresetFigures.addBlinker(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                case "7" -> added = PresetFigures.addToad(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                case "8" -> added = PresetFigures.addBeacon(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                case "9" -> added = PresetFigures.addPentaDeacathlon(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                case "10" -> added = PresetFigures.addGlider(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                case "11" -> added = PresetFigures.addLWSS(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                case "12" -> added = PresetFigures.addMWSS(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                case "13" -> added = PresetFigures.addHWSS(conwaysGameOfLife, Integer.parseInt(horPos),
                        Integer.parseInt(vertPos));
                default -> {
                    ErrorMessages.getInputErrorMessage();
                    formOfLifeMenu(conwaysGameOfLife);
                }
            }
        } else if (!InputUtils.checkEmpty(vertPos)) {
            ErrorMessages.getEmptyLineErrorMessage();
            formOfLifeMenu(conwaysGameOfLife);
        } else if (!InputUtils.checkBlank(vertPos)) {
            ErrorMessages.getBlankLineErrorMessage();
            formOfLifeMenu(conwaysGameOfLife);
        } else if (!InputUtils.checkIfPositiveInteger(vertPos)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            formOfLifeMenu(conwaysGameOfLife);
        } else if (!InputUtils.checkEmpty(horPos)) {
            ErrorMessages.getEmptyLineErrorMessage();
            formOfLifeMenu(conwaysGameOfLife);
        } else if (!InputUtils.checkBlank(horPos)) {
            ErrorMessages.getBlankLineErrorMessage();
            formOfLifeMenu(conwaysGameOfLife);
        } else if (!InputUtils.checkIfPositiveInteger(horPos)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            formOfLifeMenu(conwaysGameOfLife);
        }
        return added;
    }

    public static void addFigureSection(ConwaysGameOfLife conwaysGameOfLife) throws IOException, ParseException {
        MenuMessages.setFigureMessage();
        String quantityOfLives = InputUtils.returnLine();
        if (InputUtils.checkEmpty(quantityOfLives) && InputUtils.checkBlank(quantityOfLives) &&
                InputUtils.checkIfPositiveInteger(quantityOfLives)) {
            int quantity = Integer.parseInt(quantityOfLives);
            for (int i = 0; i < quantity; i++) {
                boolean added = formOfLifeMenu(conwaysGameOfLife);
                if (added) {
                    MenuMessages.returnBaseGrid(conwaysGameOfLife.toString());
                } else {
                    ErrorMessages.getFigureOutOfBoundsErrorMessage();
                    gameOfLifeWithPresetFigures();
                }
            }
            setTurnsSection(conwaysGameOfLife);
        } else if (!InputUtils.checkEmpty(quantityOfLives)) {
            ErrorMessages.getEmptyLineErrorMessage();
            addFigureSection(conwaysGameOfLife);
        } else if (!InputUtils.checkBlank(quantityOfLives)) {
            ErrorMessages.getBlankLineErrorMessage();
            addPointSection(conwaysGameOfLife);
        } else if (!InputUtils.checkIfPositiveInteger(quantityOfLives)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            addPointSection(conwaysGameOfLife);
        }
    }

    public static void gameOfLifeWithRandomPoints() throws IOException, ParseException {
        MenuMessages.returnGenerationInputMessage();
        String turns = InputUtils.returnLine();
        if (InputUtils.checkEmpty(turns) && InputUtils.checkBlank(turns) && InputUtils.checkIfPositiveInteger(turns)) {
            ConwaysGameOfLife conwaysGameOfLife = new ConwaysGameOfLife(10, 10);
            conwaysGameOfLife.generateRandomPoints();
            MenuMessages.returnBaseGameGrid(conwaysGameOfLife.toString());
            int generation = Integer.parseInt(turns);
            for (int i = 1; i <= generation; i++) {
                MenuMessages.returnNextGensGameGrid(String.valueOf(i), conwaysGameOfLife.nextGen().toString());
            }
        } else if (!InputUtils.checkEmpty(turns)) {
            ErrorMessages.getEmptyLineErrorMessage();
            gameOfLifeWithRandomPoints();
        } else if (!InputUtils.checkBlank(turns)) {
            ErrorMessages.getBlankLineErrorMessage();
            gameOfLifeWithRandomPoints();
        } else if (!InputUtils.checkIfPositiveInteger(turns)) {
            ErrorMessages.getNotPositiveNumberErrorMessage();
            gameOfLifeWithRandomPoints();
        }
        stackOfTasks.add(8);
        Menu.runSubMenu();
    }
}
