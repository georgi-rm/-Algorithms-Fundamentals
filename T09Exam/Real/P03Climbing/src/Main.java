import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int matrixRows = Integer.parseInt(scanner.nextLine());
        int matrixColumns = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[matrixRows][matrixColumns];
        int[][] sumsMatrix = new int[matrixRows][matrixColumns];

        for (int row = 0; row < matrix.length; row++) {
            String[] valuesOnRow = scanner.nextLine().split("\\s+");
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column] = Integer.parseInt(valuesOnRow[column]);
            }
        }

        for (int row = matrix.length - 1; row >= 0; row--) {
            for (int column = matrix[row].length - 1; column >= 0; column--) {
                if (row == matrix.length - 1 && column == matrix[row].length - 1) {
                    sumsMatrix[row][column] = matrix[row][column];
                } else if (row == matrix.length - 1) {
                    sumsMatrix[row][column] = sumsMatrix[row][column + 1] + matrix[row][column];
                } else if (column == matrix[row].length -1) {
                    sumsMatrix[row][column] = sumsMatrix[row + 1][column] + matrix[row][column];
                } else if (sumsMatrix[row][column + 1] >= sumsMatrix[row + 1][column]) {
                    sumsMatrix[row][column] = sumsMatrix[row][column + 1] + matrix[row][column];
                } else {
                    sumsMatrix[row][column] = sumsMatrix[row + 1][column] + matrix[row][column];
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int row = 0;
        int column = 0;
        path.add(matrix[row][column]);
        while (row < matrix.length -1 || column < matrix[row].length - 1) {
            if (row == matrix.length - 1) {
                column++;
            } else if (column == matrix[row].length - 1) {
                row++;
            }
            else if (sumsMatrix[row][column + 1] >= sumsMatrix[row + 1][column]) {
                column++;
            } else {
                row++;
            }
            path.add(matrix[row][column]);
        }

        Collections.reverse(path);

        System.out.println(sumsMatrix[0][0]);
        for (Integer pathValue : path) {
            System.out.printf("%d ", pathValue);
        }
    }
}
