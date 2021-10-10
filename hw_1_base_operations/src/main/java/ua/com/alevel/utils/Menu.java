package ua.com.alevel.utils;

import ua.com.alevel.firsttask.FirstTask;
import ua.com.alevel.secondtask.SecondTask;
import ua.com.alevel.thirdtask.ThirdTask;

import java.text.ParseException;
import java.util.Scanner;

public class Menu {

    public static void runOptions() {
        System.out.println("Homework 1");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Выберите опцию: ");
        System.out.println("1 - Посчитать сумму цифр в строке");
        System.out.println("2 - Отфильтровать буквы из строки и посчитать их количество вхождений в строку");
        System.out.println("3 - Определить, когда заканчивается урок");
        System.out.println("-------------------------------------------------------------------------------");
    }

    public static void runMenu() throws ParseException {
        System.out.println("Для выбора опции введите число (1/2/3): ");
        System.out.println("-------------------------------------------------------------------------------");

        try (Scanner consoleInput = new Scanner(System.in)) {
            if (consoleInput.hasNextInt()) {
                int option = consoleInput.nextInt();
                switch (option) {
                    case 1 -> FirstTask.countSumOfDigits();
                    case 2 -> SecondTask.countAndSortLetters();
                    case 3 -> ThirdTask.endLesson();
                    default -> {
                        System.out.println("Проверьте вводимые данные!");
                        System.out.println("-------------------------------------------------------------------------------");
                        runMenu();
                    }
                }
            } else {
                runMenu();
            }
        }
    }

    public static void goBackFromFirstTask(Scanner scanner) throws ParseException {
        System.out.println("Хотите вернуться обратно в меню? Для выхода введите " + " 'e' " + "(y/n/e)");
        if (scanner.hasNext()) {
            String input = scanner.next();
            switch (input) {
                case "y" -> {
                    Menu.runOptions();
                    Menu.runMenu();
                }
                case "n" -> FirstTask.countSumOfDigits();
                case "e" -> scanner.close();
                default -> {
                    System.out.println("Проверьте вводимые данные!");
                    System.out.println("-------------------------------------------------------------------------------");
                    goBackFromFirstTask(scanner);
                }
            }
        }
    }

    public static void goBackFromSecondTask(Scanner scanner) throws ParseException {
        System.out.println("Хотите вернуться обратно в меню? Для выхода введите " + " 'e' " + "(y/n/e)");
        if (scanner.hasNext()) {
            String input = scanner.next();
            switch (input) {
                case "y" -> {
                    Menu.runOptions();
                    Menu.runMenu();
                }
                case "n" -> SecondTask.countAndSortLetters();
                case "e" -> scanner.close();
                default -> {
                    System.out.println("Проверьте вводимые данные!");
                    System.out.println("-------------------------------------------------------------------------------");
                    goBackFromSecondTask(scanner);
                }
            }
        }
    }

    public static void goBackFromThirdTask(Scanner scanner) throws ParseException {
        System.out.println("Хотите вернуться обратно в меню? Для выхода введите " + " 'e' " + "(y/n/e)");
        if (scanner.hasNext()) {
            String input = scanner.next();
            switch (input) {
                case "y" -> {
                    Menu.runOptions();
                    Menu.runMenu();
                }
                case "n" -> ThirdTask.endLesson();
                case "e" -> scanner.close();
                default -> {
                    System.out.println("Проверьте вводимые данные!");
                    System.out.println("-------------------------------------------------------------------------------");
                    goBackFromThirdTask(scanner);
                }
            }
        }
    }
}

