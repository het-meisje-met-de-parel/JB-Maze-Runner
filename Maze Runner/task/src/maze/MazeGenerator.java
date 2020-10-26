package maze;

import java.util.Random;

public class MazeGenerator {

    public int[][] generation(int row, int col) {
        int [][] maze = new int[row][col];
        Random r = new Random();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean isWall = i == 0 || i == row - 1 || j == 0 || j == col - 1;
                maze[i][j] = isWall ? 1 : 0;
            }
        }
        for (int i = 2; i < row - 1; i = i + 2) {
            for (int j = 1; j < col - 1; j++) {
                maze[i][j] = row % 2 == 0 && i == row - 2 && j % 2 == 1 ? 0 : 1;
            }
            if (row % 2 == 0 && (i == row - 2 || i == row - 1)) {
                continue;
            }
            int rx = 1 + r.nextInt(col - 2);
            maze[i][rx] = 0;
        }

        maze[1][0] = 0;
        maze[row - 2 - (row % 2 == 0 ? 1 : 0)][col - 1] = 0;

        return maze;
    }

}
