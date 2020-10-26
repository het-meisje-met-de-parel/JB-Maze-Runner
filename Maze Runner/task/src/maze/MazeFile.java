package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MazeFile {

    public Maze load(String st) {
        File file = new File(st);
        try {
            Scanner scanner = new Scanner(file);
            int size = scanner.nextInt();
            int[][] mass = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    mass[i][j] = scanner.nextInt();
                }
            }
            return new Maze(mass);
        } catch (FileNotFoundException fnfe) {
            System.out.println("The file " + st + " does not exist");
            return null;
        }
    }

    public void save(String st, Maze maze) {
        try (
            PrintWriter pw = new PrintWriter(st, StandardCharsets.UTF_8);
        ) {
            pw.println(maze.getRows());
            int size = maze.getRows();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    pw.print(maze.mazeElement(i, j, true) + " ");
                }
                pw.println();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

}
