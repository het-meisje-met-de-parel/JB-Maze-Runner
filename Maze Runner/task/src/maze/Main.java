package maze;

import java.util.Scanner;

public class Main {

    public static Maze maze;

    public static void main(String[] args) {

        while (true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Generate a new maze");
            System.out.println("2. Load a maze");
            if (maze != null) {
                System.out.println("3. Save the maze");
                System.out.println("4. Display the maze");
                System.out.println("5. Find the escape.");
            }
            System.out.println("0. Exit");
            Scanner scanner = new Scanner(System.in);
            int command = Integer.parseInt(scanner.nextLine());

            if (command == 1) {
                System.out.println("Enter the size of a new maze");
                int row = Integer.parseInt(scanner.nextLine());
                int col = row;
                maze = new Maze(row, col);
                System.out.println(maze.toString());
            } else if (command == 2) {
                String st = scanner.nextLine();
                maze = new MazeFile().load(st);
            } else if (command == 3) {
                String st = scanner.nextLine();
                new MazeFile().save(st, maze);
            } else if (command == 4) {
                System.out.println(maze.toString());
            } else if (command == 5) {
                maze.findTheEscape();
                System.out.println(maze.toString(false));
            } else if (command == 0) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Incorrect option. Please try again");
            }
        }
    }

}
