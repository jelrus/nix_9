package ua.com.alevel.levels.first.horse;

public class ChessBoard {

    private static ChessBoard instance;
    private final ChessField[] grid;

    private ChessBoard() {
        grid = new ChessField[64];
    }

    public static ChessBoard getInstance() {
        if (instance == null) {
            instance = new ChessBoard();
            instance.generateBoard();
        }
        return instance;
    }

    public void generateBoard() {
        char chessLetter = '@';
        int chessNumber = 9;
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            chessNumber--;
            for (int j = 0; j < 8; j++) {
                chessLetter++;
                if (chessLetter > 72) {
                    chessLetter = 65;
                }
                instance.grid[counter++] = new ChessField(chessLetter, chessNumber);
            }
        }
    }

    public ChessField[] getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        StringBuilder chessBoardBuilder = new StringBuilder();
        for (ChessField chessField : grid) {
            if (chessField.getLetter() == 72) {
                chessBoardBuilder.append("|").append(chessField).append("|").append("\n");
            } else {
                chessBoardBuilder.append("|").append(chessField).append("|");
            }
        }
        return chessBoardBuilder.toString();
    }
}
