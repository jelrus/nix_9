package ua.com.alevel.levels.first.horse;

import java.util.ArrayList;

public class HorseMove {

    public static ArrayList<ChessField> trueMoves = new ArrayList<>();

    public static Boolean inRange(ChessField chessField) {
        ChessBoard chessBoard = ChessBoard.getInstance();
        for (ChessField field : chessBoard.getGrid()) {
            if (chessField.equals(field)) {
                return true;
            }
        }
        return false;
    }

    public static ChessField[] calculateMoves(ChessField chessField) {
        char initFieldLetter = (char) (chessField.letter - 2);
        int initFieldNumber = chessField.number + 3;
        int fieldCounter = 0;
        ChessField[] chessFields = new ChessField[25];
        for (int i = 0; i < 5; i++) {
            initFieldNumber--;
            for (int j = 0; j < 5; j++) {
                if (initFieldLetter > (chessField.getLetter() + 2)) {
                    initFieldLetter = (char) (chessField.getLetter() - 2);
                }
                chessFields[fieldCounter++] = new ChessField(initFieldLetter, initFieldNumber);
                initFieldLetter++;
            }
        }
        return chessFields;
    }

    public static void filterMoves(ChessField[] chessFields) {
        for (int i = 0; i < chessFields.length; i++) {
            if ((i % 2 != 0 || i == 12) && inRange(chessFields[i])) {
                trueMoves.add(chessFields[i]);
            }
            if (i == 7 || i == 11 || i == 13 || i == 17) {
                trueMoves.remove(chessFields[i]);
            }
        }
    }

    public static String viewAvailableMoves(ChessField chessField) {
        StringBuilder sb = new StringBuilder();
        int boardNumbers = 8;
        char boardLetters = 65;
        for (int i = 0; i < ChessBoard.getInstance().getGrid().length; i++) {
            if (i % 8 == 0) {
                sb.append("\n").append(boardNumbers--).append(" ");
            }
            if (trueMoves.contains(ChessBoard.getInstance().getGrid()[i])) {
                if (chessField.equals(ChessBoard.getInstance().getGrid()[i])) {
                    sb.append("[").append("HP").append("]");
                } else {
                    sb.append("[").append(ChessBoard.getInstance().getGrid()[i]).append("]");
                }
            } else {
                sb.append("[").append(' ').append(' ').append("]");
            }
        }
        sb.append("\n");
        for (int j = 0; j<8; j++){
            sb.append(" ").append(" ").append(" ").append(boardLetters++);
        }
        return sb.toString();
    }
}
