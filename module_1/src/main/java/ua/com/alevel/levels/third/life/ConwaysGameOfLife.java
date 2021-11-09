package ua.com.alevel.levels.third.life;

public class ConwaysGameOfLife {

    private int[][] universe;

    public ConwaysGameOfLife(int height, int width) {
        this.universe = new int[height][width];
    }

    public ConwaysGameOfLife(int[][] newUniverse) {
        this.universe = newUniverse;
    }

    public void addPoint(int verticalPos, int horizontalPos) {
        universe[verticalPos][horizontalPos] = 1;
    }

    public void deletePoint(int verticalPos, int horizontalPos) {
        universe[verticalPos][horizontalPos] = 0;
    }

    public void generateRandomPoints() {
        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[i].length; j++) {
                if (Math.random() > 0.5) {
                    addPoint(i, j);
                } else {
                    deletePoint(i, j);
                }
            }
        }
    }

    private int checkNeighbours(int verticalPos, int horizontalPos) {
        int neighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (cellExist(verticalPos + i, horizontalPos + j, universe)
                        && universe[verticalPos + i][horizontalPos + j] == 1) {
                    neighbours++;
                    if (i == 0 && j == 0) {
                        neighbours--;
                    }
                }
            }
        }
        return neighbours;
    }

    private int[][] collectNeighbours() {
        int[][] collectedNeighbours = new int[universe.length][universe[0].length];
        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[i].length; j++) {
                collectedNeighbours[i][j] = checkNeighbours(i, j);
            }
        }
        return collectedNeighbours;
    }

    private int[][] deadOrAlive(int[][] collectedNeighbours) {
        int[][] nextGen = new int[universe.length][universe[0].length];
        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[i].length; j++) {
                if (collectedNeighbours[i][j] <= 1) {
                    nextGen[i][j] = 0;
                } else if (collectedNeighbours[i][j] >= 4) {
                    nextGen[i][j] = 0;
                } else if (universe[i][j] != 0 && collectedNeighbours[i][j] == 2 | collectedNeighbours[i][j] == 3) {
                    nextGen[i][j] = 1;
                } else if (collectedNeighbours[i][j] == 3) {
                    nextGen[i][j] = 1;
                }
            }
        }
        return nextGen;
    }

    private boolean cellExist(int horizontalPos, int verticalPos, int[][] universe) {
        return verticalPos >= 0 && verticalPos < universe.length &&
                horizontalPos >= 0 && horizontalPos < universe[0].length;
    }

    public ConwaysGameOfLife nextGen() {
        universe = deadOrAlive(collectNeighbours());
        return new ConwaysGameOfLife(universe);
    }

    public int[][] getUniverse() {
        return universe;
    }

    @Override
    public String toString() {
        int a = 0;
        int b = 0;
        StringBuilder universeBuilder = new StringBuilder();
        for (int[] verticalPoint : universe) {
            if (a<10){
                universeBuilder.append("\n").append(a++).append(" ");
            } else {
                universeBuilder.append("\n").append(a++);
            }
            for (int horizontalPoint : verticalPoint) {
                if (horizontalPoint == 1) {
                    universeBuilder.append('[').append('+').append(']');
                } else {
                    universeBuilder.append('[').append(' ').append(']');
                }
            }
        }
        universeBuilder.append("\n").append("   ");
        for (int i = 0; i<universe[0].length; i++){
            if (i<9){
                universeBuilder.append(b++).append("  ");
            } else {
                universeBuilder.append(b++).append(" ");
            }
        }
        universeBuilder.append("\n");
        return universeBuilder.toString();
    }
}
