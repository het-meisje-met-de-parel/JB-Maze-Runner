package maze;

import java.util.*;

public class Maze {

    public int[][] maze;

    public Maze(int[][] maze) {
        this.maze = maze;
    }

    public Maze(int row, int col) {
        maze = new MazeGenerator().generation(row, col);
    }

    public int getCols() {
        return getRows() > 0 ? maze[0].length : 0;
    }

    public int getRows() {
        return maze.length;
    }

    public int mazeElement(int i, int j, boolean protect) {
        return protect ? Math.min (maze[i][j], 1) : maze[i][j];
    }

    private static final int[][] NEIGHBORS = {
        {-1, 0}, {0, -1}, {0, 1}, {1, 0},
    };

    private boolean isEscapeFound = false;

    public void findTheEscape() {
        if (isEscapeFound) {
            return;
        }

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {1, 0});
        maze[1][0] = -1;

        while (!que.isEmpty()) {
            int[] cell = que.poll();

            for (int i = 0; i < NEIGHBORS.length; i++) {
                int[] nei = NEIGHBORS[i];

                int nx =  cell [1] + nei [1];
                int ny =  cell [0] + nei [0];
                if (!isValidCoordinates(nx, ny) || maze[ny][nx] != 0) {
                    continue;
                }

                que.add (new int [] {ny, nx});
                maze[ny][nx] = -i - 1;
            }
        }

        int cx = getCols() - 1, cy = getRows() % 2 == 0 ? getRows() - 3 : getRows() - 2;
        while (maze[cy][cx] != -1) {
            int[] nei = NEIGHBORS[-maze[cy][cx] - 1];
            maze[cy][cx] = 2;
            cx -= nei [1]; cy -= nei [0];
        }
        maze[1][0] = 2;

        int size = getRows();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                maze[i][j] = Math.max(maze[i][j], 0);
            }
        }

        isEscapeFound = true;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean protect) {
        StringBuilder st = new StringBuilder();
        for (int[] ints : maze) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    if (anInt == 1) {
                        st.append("\u2588\u2588");
                    } else if (anInt == 2 && !protect) {
                        st.append("//");
                    } else if (!protect) {
                        st.append(String.format("%2d", anInt));
                    } else {
                        st.append("  ");
                    }
                } else {
                    st.append("  ");
                }
            }
            st.append("\n");
        }
        return st.toString();
    }


    private boolean isValidCoordinates (int x, int y) {
        return x >= 0 && x < getCols() && y >= 0 && y < getRows();
    }

}
