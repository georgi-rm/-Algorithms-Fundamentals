import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    static class ConnectedArea implements Comparable<ConnectedArea> {
        private final int row;
        private final int column;
        private int size;

        public ConnectedArea(int row, int column) {
            this.row = row;
            this.column = column;
            this.size = 0;
        }

        public void incrementSize() {
            this.size++;
        }

        @Override
        public int compareTo(ConnectedArea other) {
            int result = Integer.compare(other.size, this.size);

            if (result == 0) {
                result = Integer.compare(this.row, other.row);

                if (result == 0) {
                    result = Integer.compare(this.column, other.column);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int matrixRows = Integer.parseInt(scanner.nextLine());
        int matrixColumns = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[matrixRows][matrixColumns];

        fillMatrix(matrix, scanner);

        Set<ConnectedArea> allAreas = new TreeSet<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (matrix[row][column] == '-') {
                    ConnectedArea area = new ConnectedArea(row, column);
                    traverseArea(row, column, area, matrix);
                    allAreas.add(area);
                }
            }
        }

        System.out.printf("Total areas found: %d%n", allAreas.size());
        int numberOfArea = 1;
        for (ConnectedArea area : allAreas) {
            System.out.printf("Area #%d at (%d, %d), size: %d%n", numberOfArea++, area.row, area.column, area.size);
        }
    }

    private static void traverseArea(int row, int column, ConnectedArea area, char[][] matrix) {
        if (isNotInBounds(row, column, matrix) || matrix[row][column] != '-') {
            return;
        }

        matrix[row][column] = 'V';
        area.incrementSize();
        traverseArea(row + 1, column, area, matrix);
        traverseArea(row - 1, column, area, matrix);
        traverseArea(row, column + 1, area, matrix);
        traverseArea(row, column - 1, area, matrix);
    }

    private static boolean isNotInBounds(int row, int column, char[][] matrix) {
        return row < 0 || row >= matrix.length || column < 0 || column >= matrix[row].length;
    }


    private static void fillMatrix(char[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column] = line.charAt(column);
            }
        }
    }
}
