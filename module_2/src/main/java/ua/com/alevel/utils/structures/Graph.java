package ua.com.alevel.utils.structures;

import ua.com.alevel.utils.structures.exceptions.NodeNotFoundByIndexException;
import ua.com.alevel.utils.structures.exceptions.NodeNotFoundByNameException;

import java.util.LinkedHashMap;
import java.util.Map;

public class Graph {

    public Map<Node, Map<Node, Arc>> adjacentNodes;


    public Graph() {
        adjacentNodes = new LinkedHashMap<>();
    }

    public void addNode(String name, int index) {
        adjacentNodes.putIfAbsent(new Node(name, index), new LinkedHashMap<>());
    }

    public Node getNode(int index) {
        for (Node node : adjacentNodes.keySet()) {
            if (index == node.getIndex()) {
                return node;
            }
        }
        throw new NodeNotFoundByIndexException("Node with index " + index + " not found!");
    }

    public Node getNode(String name) {
        for (Node node : adjacentNodes.keySet()) {
            if (name.equals(node.getName())) {
                return node;
            }
        }
        throw new NodeNotFoundByNameException("Node with name " + name + " not found!");
    }

    public void removeNode(int index) {
        Node removed = getNode(index);
        adjacentNodes.values().forEach(e -> e.remove(removed));
        adjacentNodes.remove(removed);
    }

    public void addArc(boolean bidirectional, Node start, Node end, int arcValue) {
        if (bidirectional) {
            adjacentNodes.get(start).put(end, new Arc(arcValue));
            adjacentNodes.get(end).put(start, new Arc(arcValue));
        } else {
            adjacentNodes.get(start).put(end, new Arc(arcValue));
        }
    }

    public int getArcValue(Node first, Node second) {
        if (areAdjacent(first, second)) {
            for (Map.Entry<Node, Map<Node, Arc>> nodes : adjacentNodes.entrySet()) {
                if (nodes.getKey().equals(first)) {
                    for (Map.Entry<Node, Arc> subNodes : nodes.getValue().entrySet()) {
                        if (second.equals(subNodes.getKey())) {
                            return subNodes.getValue().getValue();
                        }
                    }
                }
            }
        }
        return 0;
    }

    public void setArcValue(boolean bidirectional, Node first, Node second, int value) {
        if (bidirectional) {
            if (areAdjacent(first, second)) {
                for (Map.Entry<Node, Map<Node, Arc>> nodes : adjacentNodes.entrySet()) {
                    if (nodes.getKey().equals(first) || nodes.getKey().equals(second)) {
                        for (Map.Entry<Node, Arc> subNodes : nodes.getValue().entrySet()) {
                            if (first.equals(subNodes.getKey()) || second.equals(subNodes.getKey())) {
                                subNodes.setValue(new Arc(value));
                            }
                        }
                    }
                }
            }
        } else {
            if (areAdjacent(first, second)) {
                for (Map.Entry<Node, Map<Node, Arc>> nodes : adjacentNodes.entrySet()) {
                    if (nodes.getKey().equals(first)) {
                        for (Map.Entry<Node, Arc> subNodes : nodes.getValue().entrySet()) {
                            if (second.equals(subNodes.getKey())) {
                                subNodes.setValue(new Arc(value));
                            }
                        }
                    }
                }
            }
        }
    }

    public void removeArc(boolean bidirectional, int startNodeIndex, int endNodeIndex) {
        Node start = getNode(startNodeIndex);
        Node end = getNode(endNodeIndex);
        if (bidirectional) {
            for (Node node : adjacentNodes.keySet()) {
                if (node.equals(start)) {
                    adjacentNodes.get(node).keySet().remove(end);
                } else if (node.equals(end)) {
                    adjacentNodes.get(node).keySet().remove(start);
                }
            }
        } else {
            for (Node node : adjacentNodes.keySet()) {
                if (node.equals(start)) {
                    adjacentNodes.get(node).keySet().remove(end);
                }
            }
        }
    }

    public void clearGraph() {
        adjacentNodes.clear();
    }

    public boolean areAdjacent(Node initial, Node checked) {
        if (adjacentNodes.containsKey(initial)) {
            for (Map.Entry<Node, Map<Node, Arc>> nodes : adjacentNodes.entrySet()) {
                if (nodes.getKey().equals(initial)) {
                    return nodes.getValue().containsKey(checked);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public int neighboursCount(Node node) {
        return adjacentNodes.get(node).entrySet().size();
    }

    public int[][] adjacencyMatrix() {
        int[][] matrix = new int[adjacentNodes.size()][adjacentNodes.size()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = getArcValue(getNode(i), getNode(j));
                }
            }
        }
        return matrix;
    }

    public int[][] prepareForFloydWarshall(int[][] matrix) {
        int length = matrix.length;
        int inf = 9999999;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (matrix[i][j] == 0 && i != j) {
                    matrix[i][j] = inf;
                }
            }
        }
        return matrix;
    }

    public void algorithmFloydWarshall(int[][] matrix) {
        int length = matrix.length;
        matrix = prepareForFloydWarshall(matrix);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    if (matrix[j][i] + matrix[i][k] < matrix[j][k]) {
                        matrix[j][k] = matrix[j][i] + matrix[i][k];
                    }
                }
            }
        }
    }

    public int shortestPathValue(Node start, Node end) {
        int[][] matrix = adjacencyMatrix();
        prepareForFloydWarshall(matrix);
        algorithmFloydWarshall(matrix);
        return matrix[start.getIndex()][end.getIndex()];
    }

    public String adjacencyMatrixToString(int[][] matrix) {
        StringBuilder matrixBuilder = new StringBuilder();
        matrixBuilder.append("    ");
        for (int i = 0; i < matrix.length; i++) {
            matrixBuilder.append(i).append(" ");
        }
        matrixBuilder.append("\n");
        for (int k = 0; k < matrix.length; k++) {
            matrixBuilder.append(k).append(" | ");
            for (int j = 0; j < matrix[0].length; j++) {
                matrixBuilder.append(matrix[k][j]).append(" ");
            }
            matrixBuilder.append("\n");
        }
        return matrixBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder graphBuilder = new StringBuilder();
        for (Map.Entry<Node, Map<Node, Arc>> nodes : adjacentNodes.entrySet()) {
            graphBuilder.append(nodes.getKey()).append("\n");
            for (Map.Entry<Node, Arc> subNodes : nodes.getValue().entrySet()) {
                graphBuilder.append("  to ").append(subNodes.getKey().getName())
                        .append(" (").append(subNodes.getKey().getIndex()).append(") ")
                        .append(", cost = ").append(subNodes.getValue()).append("\n");
            }
        }
        return graphBuilder.toString();
    }
}