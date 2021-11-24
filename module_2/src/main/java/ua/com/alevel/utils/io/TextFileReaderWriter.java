package ua.com.alevel.utils.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileReaderWriter {

    public static final String DATES_FILE_INPUT_PATH = "module_2/src/main/resources/text_files/input_files/dates.txt";
    public static final String NAMES_FILE_INPUT_PATH = "module_2/src/main/resources/text_files/input_files/names.txt";
    public static final String CITIES_FILE_INPUT_PATH = "module_2/src/main/resources/text_files/input_files/cities.txt";
    public static final String DATES_FILE_OUTPUT_PATH = "module_2/src/main/resources/text_files/output_files/dates.txt";
    public static final String NAMES_FILE_OUTPUT_PATH = "module_2/src/main/resources/text_files/output_files/names.txt";
    public static final String CITIES_FILE_OUTPUT_PATH = "module_2/src/main/resources/text_files/output_files/cities.txt";
    public static final String NAMES_REPOSITORY_INPUT_PATH = "module_2/src/main/resources/text_files/random_repository/names_repository.txt";
    public static final String CITIES_REPOSITORY_INPUT_PATH = "module_2/src/main/resources/text_files/random_repository/cities_repository.txt";

    public static List<String> fileToList(String path) throws IOException {
        File textFile = new File(path);
        BufferedReader textReader = new BufferedReader(new FileReader(textFile));
        ArrayList<String> textLines = new ArrayList<>();
        while (textReader.ready()) {
            String line = textReader.readLine();
            textLines.add(line);
        }
        return textLines;
    }

    public static void stringToFile(String text, String path) throws IOException {
        File textFile = new File(path);
        BufferedWriter textWriter = new BufferedWriter(new FileWriter(textFile));
        textWriter.write(text);
        textWriter.close();
    }
}