package ua.com.alevel.tasks;

import ua.com.alevel.utils.io.TextFileReaderWriter;
import ua.com.alevel.utils.structures.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Cities {

    private static final Graph GRAPH = new Graph();
    private static final ArrayList<String> NAMES = new ArrayList<>();
    private static final ArrayList<Integer> ITERATORS = new ArrayList<>();
    private static final ArrayList<String> COORDINATES = new ArrayList<>();
    private static final ArrayList<String> ROUTES = new ArrayList<>();

    private static void parseDataToLists() throws IOException {
        BufferedReader TASK_READER = new BufferedReader(new FileReader(TextFileReaderWriter.CITIES_FILE_INPUT_PATH));
        String data;
        while ((data = TASK_READER.readLine()) != null) {
            if (data.matches("^[\\D]+") && !data.contains(" ")) {
                NAMES.add(data);
            }
            if (data.matches("^[\\d]+")) {
                int iterator = Integer.parseInt(data);
                ITERATORS.add(iterator);
            }
            if (data.matches("^[\\d]+\\s+[\\d]+")) {
                COORDINATES.add(data);
            }
            if (data.matches("^[\\D]+") && data.contains(" ")) {
                ROUTES.add(data);
            }
        }
    }

    private static void buildNodes() {
        for (int i = 0; i < NAMES.size(); i++) {
            GRAPH.addNode(NAMES.get(i), i);
        }
    }

    private static void buildArcs() {
        int index = 0;
        int endPointIndex = 0;
        for (int i = 0; i < ITERATORS.size(); i++) {
            if (i != 0 && i != ITERATORS.size() - 1) {
                int iterator = ITERATORS.get(i);
                for (int j = 0; j < iterator; j++) {
                    String[] endPointData = COORDINATES.get(endPointIndex).split(" ");
                    endPointIndex++;
                    GRAPH.addArc(true, GRAPH.getNode(index),
                            GRAPH.getNode(Integer.parseInt(endPointData[0]) - 1),
                            Integer.parseInt(endPointData[1]));
                }
                index++;
            }
        }
    }

    private static String getShortestPath() {
        StringBuilder shortestPathBuilder = new StringBuilder();
        for (int i = 0; i < ITERATORS.get(ITERATORS.size() - 1); i++) {
            String[] route = ROUTES.get(i).split(" ");
            shortestPathBuilder.append("Кратчайший путь от ").append(GRAPH.getNode(route[0]).getName())
                    .append(" до ").append(GRAPH.getNode(route[1]).getName())
                    .append(" - ")
                    .append(GRAPH.shortestPathValue(GRAPH.getNode(route[0]), GRAPH.getNode(route[1])))
                    .append("\n");
        }
        return shortestPathBuilder.toString();
    }

    public static void writeShortestPath() throws IOException {
        parseDataToLists();
        buildNodes();
        buildArcs();
        TextFileReaderWriter.stringToFile(getShortestPath(), TextFileReaderWriter.CITIES_FILE_OUTPUT_PATH);
        NAMES.clear();
        ITERATORS.clear();
        COORDINATES.clear();
        ROUTES.clear();
        GRAPH.clearGraph();
    }
}