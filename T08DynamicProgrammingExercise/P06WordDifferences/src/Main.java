import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] firstText = scanner.nextLine().toCharArray();
        char[] secondText = scanner.nextLine().toCharArray();

        int[][] matrix = new int[secondText.length + 1][firstText.length + 1];

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (row == 0) {
                    matrix[row][column] = column;
                } else if (column == 0) {
                    matrix[row][column] = row;
                } else if (firstText[column - 1] == secondText[row - 1]) {
                    matrix[row][column] = matrix[row - 1][column - 1];
                } else {
                    matrix[row][column] = Math.min(matrix[row - 1][column], matrix[row][column - 1]) + 1;
                }
            }
        }

        System.out.printf("Deletions and Insertions: %d", matrix[secondText.length][firstText.length]);
    }
}
