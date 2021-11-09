package ua.com.alevel.levels.second.brackets;

import java.util.Objects;

public class BracketsEqualityDetector {

    public static boolean bracketsEquals(String input) {
        int roundOpenBracket = 0;
        int roundClosedBracket = 0;
        int figureOpenBracket = 0;
        int figureClosedBracket = 0;
        int squareOpenBracket = 0;
        int squareClosedBracket = 0;
        String[] splitString = input.split("");
        for (String s : splitString) {
            if (Objects.equals(s, "(")) {
                roundOpenBracket++;
            } else if (Objects.equals(s, ")")) {
                roundClosedBracket++;
            } else if (Objects.equals(s, "{")) {
                figureOpenBracket++;
            } else if (Objects.equals(s, "}")) {
                figureClosedBracket++;
            } else if (Objects.equals(s, "[")) {
                squareOpenBracket++;
            } else if (Objects.equals(s, "]")) {
                squareClosedBracket++;
            }
        }
        boolean rb = (roundOpenBracket == roundClosedBracket);
        boolean fb = (figureOpenBracket == figureClosedBracket);
        boolean sb = (squareOpenBracket == squareClosedBracket);
        return (rb && fb && sb);
    }
}
