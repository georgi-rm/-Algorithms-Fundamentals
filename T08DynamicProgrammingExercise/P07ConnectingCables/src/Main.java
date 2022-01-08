import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] cables = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] cablesInOrder = new int[cables.length];

        for (int i = 0; i < cablesInOrder.length; i++) {
            cablesInOrder[i] = i + 1;
        }

        int[][] matrix = new int[cables.length + 1][cables.length + 1];

        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[row].length; column++) {
                if (cablesInOrder[column - 1] == cables[row - 1]) {
                    matrix[row][column] = matrix[row - 1][column - 1] + 1;
                } else {
                    matrix[row][column] = Math.max(matrix[row - 1][column], matrix[row][column - 1]);
                }
            }
        }

        System.out.printf("Maximum pairs connected: %d", matrix[cables.length][cables.length]);
    }
}
