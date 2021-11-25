package ua.com.alevel.utils.random;

import ua.com.alevel.utils.io.TextFileReaderWriter;

import java.io.IOException;
import java.util.*;

public class GenerateRandomCitiesUtil {

    private static final List<String> GENERATED_CITIES = new ArrayList<>();
    private static List<String> CITIES;

    private static void generateCities(int quantity) throws IOException {
        populateCities();
        Set<String> uniqueCities = new LinkedHashSet<>();
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            int cityIndex = random.nextInt(CITIES.size());
            uniqueCities.add(CITIES.get(cityIndex));
        }
        GENERATED_CITIES.addAll(uniqueCities);
    }

    private static String generateArcs() {
        Random random = new Random();
        StringBuilder endPointBuilder = new StringBuilder();
        endPointBuilder.append(GENERATED_CITIES.size()).append("\n");
        for (int i = 0; i < GENERATED_CITIES.size(); i++) {
            endPointBuilder.append(GENERATED_CITIES.get(i)).append("\n");
            int arcs = random.nextInt(2, GENERATED_CITIES.size());
            for (int j = 0; j < arcs; j++) {
                if (i != j) {
                    endPointBuilder.append(j + 1).append(" ").append(random.nextInt(1, 10)).append("\n");
                }
            }
        }
        return endPointBuilder.toString();
    }

    private static String calculateArcs() {
        String source = generateArcs() + "a";
        String[] split = source.split("\n");
        StringBuilder correctedString = new StringBuilder();
        List<Integer> counters = new ArrayList<>();
        int count = 0;
        for (String s : split) {
            if (s.matches("^[\\d]+\\s+[\\d]+")) {
                count++;
            } else if (s.matches("^[\\D]+")) {
                if (count != 0) {
                    counters.add(count);
                    count = 0;
                }
            }
        }
        int counter = 0;
        for (String strings : split) {
            if (strings.matches("^[\\D]+") && !strings.equals("a")) {
                correctedString.append(strings).append("\n");
                correctedString.append(counters.get(counter++)).append("\n");
            } else {
                correctedString.append(strings).append("\n");
            }
        }
        return correctedString.toString();
    }

    private static String generateRoutes() {
        StringBuilder routeBuilder = new StringBuilder();
        Random random = new Random();
        int routes = 5;
        routeBuilder.append(routes).append("\n");
        for (int i = 0; i < routes; i++) {
            String firstCity = GENERATED_CITIES.get(random.nextInt(GENERATED_CITIES.size()));
            String secondCity = GENERATED_CITIES.get(random.nextInt(GENERATED_CITIES.size()));
            if (firstCity.equals(secondCity)) {
                int index = firstCity.indexOf(firstCity);
                secondCity = GENERATED_CITIES.get(index);
            }
            routeBuilder.append(firstCity).append(" ").append(secondCity).append("\n");
        }
        return routeBuilder.toString();
    }

    private static void populateCities() throws IOException {
        CITIES = TextFileReaderWriter.fileToList(TextFileReaderWriter.CITIES_REPOSITORY_INPUT_PATH);
    }

    public static void generateTask(int quantity) throws IOException {
        StringBuilder taskBuilder = new StringBuilder();
        generateCities(quantity);
        generateArcs();
        taskBuilder.append(calculateArcs());
        taskBuilder.delete(taskBuilder.length() - 2, taskBuilder.length());
        taskBuilder.append(generateRoutes());
        taskBuilder.delete(taskBuilder.length() - 1, taskBuilder.length());
        TextFileReaderWriter.stringToFile(taskBuilder.toString(), TextFileReaderWriter.CITIES_FILE_INPUT_PATH);
        GENERATED_CITIES.clear();
    }
}