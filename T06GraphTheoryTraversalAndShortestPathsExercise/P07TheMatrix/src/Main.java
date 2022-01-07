import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] dimensions = scanner.nextLine().split("\\s+");

        int matrixRows = Integer.parseInt(dimensions[0]);
        int matrixColumns = Integer.parseInt(dimensions[1]);

        char[][] matrix = new char[matrixRows][matrixColumns];

        for (int row = 0; row < matrixRows; row++) {
            String[] lineData = scanner.nextLine().split("\\s+");
            for (int column = 0; column < matrixColumns; column++) {
                matrix[row][column] = lineData[column].charAt(0);
            }
        }

        char replaceCharacter = scanner.nextLine().charAt(0);

        String[] coordinates = scanner.nextLine().split("\\s+");

        int startRow = Integer.parseInt(coordinates[0]);
        int startColumn = Integer.parseInt(coordinates[1]);

        fillArea(startRow, startColumn, matrix[startRow][startColumn], replaceCharacter, matrix);

        StringBuilder output = new StringBuilder();
        for (char[] rowOfSymbols : matrix) {
            for (char symbol : rowOfSymbols) {
                output.append(symbol);
            }
            output.append(System.lineSeparator());
        }

        System.out.println(output.toString().trim());
    }

    private static void fillArea(int row, int column, char symbolToReplace, char replacementSymbol, char[][] matrix) {
        if (isNotInBounds(row, column, matrix)) {
            return;
        }

        if (matrix[row][column] != symbolToReplace) {
            return;
        }

        matrix[row][column] = replacementSymbol;

        fillArea(row + 1, column, symbolToReplace, replacementSymbol, matrix);
        fillArea(row - 1, column, symbolToReplace, replacementSymbol, matrix);
        fillArea(row, column + 1, symbolToReplace, replacementSymbol, matrix);
        fillArea(row, column - 1, symbolToReplace, replacementSymbol, matrix);
    }

    private static boolean isNotInBounds(int row, int column, char[][] matrix) {
        return row < 0 || row >= matrix.length || column < 0 || column >= matrix[row].length;
    }
}
