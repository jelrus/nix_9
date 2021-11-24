package ua.com.alevel.utils.random;

import ua.com.alevel.utils.io.TextFileReaderWriter;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GenerateRandomNameUtil {

    private static List<String> NAMES;

    public static void generateRandomNameFromList(int bound) throws IOException {
        Random randomIndex = new Random();
        StringBuilder nameBuilder = new StringBuilder();
        populateList();
        for (int i = 0; i < bound; i++) {
            int index = randomIndex.nextInt(NAMES.size());
            nameBuilder.append(NAMES.get(index));
            nameBuilder.append("\n");
        }
        TextFileReaderWriter.stringToFile(nameBuilder.toString(), TextFileReaderWriter.NAMES_FILE_INPUT_PATH);
    }

    private static void populateList() throws IOException {
        NAMES = TextFileReaderWriter.fileToList(TextFileReaderWriter.NAMES_REPOSITORY_INPUT_PATH);
    }
}