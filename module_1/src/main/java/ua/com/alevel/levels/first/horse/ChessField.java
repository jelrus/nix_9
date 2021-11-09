package ua.com.alevel.levels.first.horse;

import java.util.Objects;

public class ChessField {

    public char letter;
    public int number;

    public ChessField(char letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public char getLetter() {
        return letter;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return letter + "" + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessField that)) return false;
        return getLetter() == that.getLetter() && getNumber() == that.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLetter(), getNumber());
    }
}
