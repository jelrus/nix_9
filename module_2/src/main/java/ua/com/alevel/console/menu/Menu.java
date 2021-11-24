package ua.com.alevel.console.menu;

import ua.com.alevel.console.messages.MenuMessages;
import ua.com.alevel.tasks.Cities;
import ua.com.alevel.tasks.Dates;
import ua.com.alevel.tasks.Names;
import ua.com.alevel.utils.io.TextFileReaderWriter;
import ua.com.alevel.utils.random.GenerateRandomCitiesUtil;
import ua.com.alevel.utils.random.GenerateRandomDateUtil;
import ua.com.alevel.utils.random.GenerateRandomNameUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Menu {

    public static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void runMenu() throws IOException {
        MenuMessages.mainMenuText();
        String option = input.readLine().toLowerCase(Locale.ROOT);
        switch (option) {
            case "1" -> {
                GenerateRandomDateUtil.generateRandomDatesWithStringContamination(500);
                MenuMessages.datesGenerationSuccess();
                runMenu();
            }
            case "2" -> {
                GenerateRandomNameUtil.generateRandomNameFromList(500);
                MenuMessages.namesGenerationSuccess();
                runMenu();
            }
            case "3" -> {
                GenerateRandomCitiesUtil.generateTask(5);
                MenuMessages.citiesGenerationSuccess();
                runMenu();
            }
            case "4" -> {
                Dates.writeFilteringResult(TextFileReaderWriter.DATES_FILE_INPUT_PATH,
                        TextFileReaderWriter.DATES_FILE_OUTPUT_PATH);
                MenuMessages.datesResolvingSuccess();
                runMenu();
            }
            case "5" -> {
                Names.writeResult(TextFileReaderWriter.NAMES_FILE_INPUT_PATH,
                        TextFileReaderWriter.NAMES_FILE_OUTPUT_PATH);
                MenuMessages.namesResolvingSuccess();
                runMenu();
            }
            case "6" -> {
                Cities.writeShortestPath();
                MenuMessages.citiesResolvingSuccess();
                runMenu();
            }
            case "e" -> System.exit(0);
            default -> {
                MenuMessages.error();
                runMenu();
            }
        }
    }
}