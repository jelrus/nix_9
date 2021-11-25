package ua.com.alevel.utils.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextFileReaderWriter {

    public static final String DATES_FILE_INPUT_PATH = "text_files\\input_files\\dates.txt";
    public static final String NAMES_FILE_INPUT_PATH = "text_files\\input_files\\names.txt";
    public static final String CITIES_FILE_INPUT_PATH = "text_files\\input_files\\cities.txt";
    public static final String DATES_FILE_OUTPUT_PATH = "text_files\\output_files\\dates.txt";
    public static final String NAMES_FILE_OUTPUT_PATH = "text_files\\output_files\\names.txt";
    public static final String CITIES_FILE_OUTPUT_PATH = "text_files\\output_files\\cities.txt";
    public static final String NAMES_REPOSITORY_INPUT_PATH = "text_files\\random_repository\\names_repository.txt";
    public static final String CITIES_REPOSITORY_INPUT_PATH = "text_files\\random_repository\\cities_repository.txt";

    public static List<String> fileToList(String path) throws IOException {
        Path userPath = Paths.get(System.getProperty("user.dir"));
        String uPath = userPath.toString();
        ArrayList<String> textLines = new ArrayList<>();
        if (uPath.contains("module_2")) {
            BufferedReader textReader = new BufferedReader(new FileReader(uPath + "\\" + path));
            while (textReader.ready()) {
                String line = textReader.readLine();
                textLines.add(line);
            }
        } else {
            uPath = uPath + "\\module_2";
            BufferedReader textReader = new BufferedReader(new FileReader(uPath + "\\" + path));
            while (textReader.ready()) {
                String line = textReader.readLine();
                textLines.add(line);
            }
        }
        return textLines;
    }

    public static void stringToFile(String text, String path) throws IOException {
        Path userPath = Paths.get(System.getProperty("user.dir"));
        String uPath = userPath.toString();
        if (uPath.contains("module_2")) {
            BufferedWriter textWriter = new BufferedWriter(new FileWriter(uPath + "\\" + path));
            textWriter.write(text);
            textWriter.close();
        } else {
            uPath = uPath + "\\module_2";
            BufferedWriter textWriter = new BufferedWriter(new FileWriter(uPath + "\\" + path));
            textWriter.write(text);
            textWriter.close();
        }
    }
}