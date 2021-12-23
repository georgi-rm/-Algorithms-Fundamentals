import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Character> currentPath;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int labyrinthRows = Integer.parseInt(scanner.nextLine());
        int labyrinthColumns = Integer.parseInt(scanner.nextLine());

        char[][] labyrinth = new char[labyrinthRows][labyrinthColumns];

        for (int row = 0; row < labyrinthRows; row++) {
            String line = scanner.nextLine();
            for (int column = 0; column < line.length(); column++) {
                labyrinth[row][column] = line.charAt(column);
            }
        }

        currentPath = new ArrayList<>();

        findAllPathsInLabyrinth(0, 0, labyrinth, null);
    }

    private static void findAllPathsInLabyrinth(int row, int column, char[][] labyrinth, Character direction) {
        if (isOutOfBounds(row, column, labyrinth) || labyrinth[row][column] == '*' || labyrinth[row][column] == 'V') {
            return;
        }

        if (direction != null) {
            currentPath.add(direction);
        }

        if (labyrinth[row][column] == 'e') {
            for (int i = 0; i < currentPath.size(); i++) {
                System.out.printf("%c", currentPath.get(i));
            }
            System.out.println();
        } else {
            labyrinth[row][column] = 'V';
            findAllPathsInLabyrinth(row, column + 1, labyrinth, 'R');
            findAllPathsInLabyrinth(row + 1, column, labyrinth, 'D');
            findAllPathsInLabyrinth(row, column - 1, labyrinth, 'L');
            findAllPathsInLabyrinth(row - 1, column, labyrinth, 'U');
            labyrinth[row][column] = '-';
        }
        if (direction != null) {
            currentPath.remove(currentPath.size() - 1);
        }
    }

    private static boolean isOutOfBounds(int row, int column, char[][] labyrinth) {
        return (row < 0) || (row >= labyrinth.length) || (column < 0) || (column >= labyrinth[row].length);
    }
}
