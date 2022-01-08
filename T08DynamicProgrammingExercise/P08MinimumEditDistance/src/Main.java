import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int costToReplace = Integer.parseInt(scanner.nextLine());
        int costToInsert = Integer.parseInt(scanner.nextLine());
        int costToDelete = Integer.parseInt(scanner.nextLine());

        char[] firstText = scanner.nextLine().toCharArray();
        char[] secondText = scanner.nextLine().toCharArray();

        int[][] matrix = new int[firstText.length + 1][secondText.length + 1];

        for (int row = 1; row < matrix.length; row++) {
            matrix[row][0] = matrix[row - 1][0] + costToDelete;
        }

        for (int column = 1; column < matrix[matrix.length - 1].length; column++) {
            matrix[0][column] = matrix[0][column - 1] + costToInsert;
        }

        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[row].length; column++) {
                if (firstText[row - 1] == secondText[column - 1]) {
                    matrix[row][column] = matrix[row - 1][column - 1];
                } else {
                    int insert = matrix[row][column - 1] + costToInsert;
                    int replace = matrix[row - 1][column - 1] + costToReplace;
                    int delete = matrix[row - 1][column] + costToDelete;
                    matrix[row][column] = Math.min(insert, Math.min(replace,delete));
                }
            }
        }

        System.out.printf("Minimum edit distance: %d", matrix[firstText.length][secondText.length]);
    }
}
