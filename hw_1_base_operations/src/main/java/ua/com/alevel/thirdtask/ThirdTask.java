package ua.com.alevel.thirdtask;

import ua.com.alevel.utils.Menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ThirdTask {
    public static void endLesson() throws ParseException {
        try (Scanner taskInput = new Scanner(System.in)) {
            System.out.print("Введите число от 1 до 10: ");
            if (taskInput.hasNextInt()) {
                int lesson = taskInput.nextInt();
                if (lesson<=10 && lesson>0) {
                    int initialHours = 9;
                    int hoursToMinutes = initialHours * 60;
                    int initialMinutes = 0;
                    int duration = 45;
                    int minBreak = 5;
                    int maxBreak = 15;

                    int time = hoursToMinutes + initialMinutes + duration * lesson +
                            (lesson / 2) * minBreak + ((lesson - 1) / 2) * maxBreak;
                    int endHours = time / 60;
                    int endMinutes = time % 60;

                    String timeValue = endHours + " " + endMinutes;
                    SimpleDateFormat originalTimeFormat = new SimpleDateFormat("HHmm");
                    Date date = originalTimeFormat.parse(timeValue);

                    SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm");
                    String formattedDate = newFormat.format(date);

                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("Входные данные: " + lesson);
                    System.out.println("Выходные данные: " + formattedDate);
                    System.out.println("-------------------------------------------------------------------------------");

                    Menu.goBackFromThirdTask(taskInput);
                } else {
                    endLesson();
                }
            } else {
                endLesson();
            }
        }
    }
}
