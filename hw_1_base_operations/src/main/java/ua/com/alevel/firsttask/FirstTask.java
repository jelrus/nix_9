package ua.com.alevel.firsttask;

import ua.com.alevel.utils.Menu;

import java.text.ParseException;
import java.util.Scanner;

public class FirstTask {
    public static void countSumOfDigits() {
        try (Scanner taskInput = new Scanner(System.in)) {
            System.out.print("Введите строку: ");

            if (taskInput.hasNext()) {
                String input = taskInput.nextLine();
                int result = 0;

                for (int i = 0; i < input.length(); i++) {
                    String substring = input.substring(i, i + 1);
                    if (substring.matches("[0-9]")) {
                        result += Integer.parseInt(substring);
                    }
                }

                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Входные данные: " + input);
                System.out.println("Выходные данные: " + result);
                System.out.println("-------------------------------------------------------------------------------");
                Menu.goBackFromFirstTask(taskInput);
            } else {
                countSumOfDigits();
            }
        } catch (ParseException e) {
            System.err.println("Что-то пошло не так, проверьте данные или перезапустите программу");
        }
    }
}

