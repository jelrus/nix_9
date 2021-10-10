package ua.com.alevel.secondtask;

import ua.com.alevel.utils.Menu;

import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class SecondTask {
    public static void countAndSortLetters() {
        try (Scanner taskInput = new Scanner(System.in)) {
            System.out.print("Введите строку: ");

            if (taskInput.hasNextLine()) {
                String input = taskInput.nextLine();
                Map<Character, Integer> map = new TreeMap<>();
                int n = 1;
                for (int i = 0; i < input.length(); i++) {
                    if (Character.isAlphabetic(input.charAt(i))) {
                        map.put(input.charAt(i), map.getOrDefault(input.charAt(i), 0) + 1);
                    }
                }

                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Входные данные: " + input);
                System.out.println("Выходные данные: ");

                for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                    System.out.println(n++ + ". " + entry.getKey() + " - " + entry.getValue());
                }
                System.out.println("-------------------------------------------------------------------------------");
                Menu.goBackFromSecondTask(taskInput);
            } else {
                countAndSortLetters();
            }
        } catch (ParseException e) {
            System.err.println("Что-то пошло не так, проверьте данные или перезапустите программу");
        }
    }
}

