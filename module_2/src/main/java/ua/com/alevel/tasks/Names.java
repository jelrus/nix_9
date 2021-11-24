package ua.com.alevel.tasks;

import ua.com.alevel.utils.io.TextFileReaderWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Names {

    private static String nameIsUnique(List<String> names) {
        List<String> uniques = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            int count = Collections.frequency(names, names.get(i));
            if (count == 1) {
                uniques.add(names.get(i));
            }
        }
        return uniques.get(0);
    }

    public static void writeResult(String inputPath, String outputPath) throws IOException {
        List<String> initialNames = TextFileReaderWriter.fileToList(inputPath);
        TextFileReaderWriter.stringToFile(nameIsUnique(initialNames), outputPath);
    }
}