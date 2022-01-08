import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int matrixRows = Integer.parseInt(scanner.nextLine());
        int matrixColumns = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[matrixRows][matrixColumns];
        int[][] matrixSums;

        for (int row = 0; row < matrixRows; row++) {
            String[] rowValues = scanner.nextLine().split("\\s+");

            for (int column = 0; column < matrixColumns; column++) {
                matrix[row][column] = Integer.parseInt(rowValues[column]);
            }
        }

        matrixSums = new int[matrixRows][matrixColumns];

        matrixSums[0][0] = matrix[0][0];

        for (int column = 1; column < matrixColumns; column++) {
            matrixSums[0][column] = matrixSums[0][column - 1] + matrix[0][column];
        }

        for (int row = 1; row < matrixSums.length; row++) {
            matrixSums[row][0] = matrixSums[row - 1][0] + matrix[row][0];
        }

        for (int row = 1; row < matrixSums.length; row++) {
            for (int column = 1; column < matrixSums[row].length; column++) {
                matrixSums[row][column] = Math.max(matrixSums[row - 1][column], matrixSums[row][column - 1]) + matrix[row][column];
            }
        }

        int row = matrixRows - 1;
        int column = matrixColumns - 1;

        List<String> output = new ArrayList<>();
        output.add(String.format("[%d, %d]", row, column));

        while (row > 0 || column > 0) {
            if (row == 0) {
                column--;
            } else if (column == 0) {
                row--;
            } else if (matrixSums[row][column - 1] >= matrixSums[row - 1][column]) {
                column--;
            } else {
                row--;
            }
            output.add(String.format("[%d, %d]", row, column));
        }

        Collections.reverse(output);
        System.out.println(String.join(" ", output));
    }
}
