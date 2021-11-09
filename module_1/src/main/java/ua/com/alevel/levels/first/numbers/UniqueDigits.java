package ua.com.alevel.levels.first.numbers;

import java.util.HashSet;
import java.util.Set;

public class UniqueDigits {

    public static int countUniqueDigits(String input) {
        String[] members = input.replaceAll("[^0-9]", " ").split("");
        Set<Integer> parsedIntegers = new HashSet<>();
        for (String member : members) {
            if (!member.isEmpty() && !member.isBlank()) {
                parsedIntegers.add(Integer.parseInt(member));
            }
        }
        return parsedIntegers.size();
    }
}
