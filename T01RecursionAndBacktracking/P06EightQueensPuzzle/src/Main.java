import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int boardRows = 8;
        int boardColumns = 8;
        char[][] chessBoard = new char[boardRows][boardColumns];
        int[][] attackedPositions = new int[boardRows][boardColumns];

        for (int row = 0; row < chessBoard.length; row++) {
            Arrays.fill(chessBoard[row], '-');
        }

        printEightQueens(chessBoard, attackedPositions, 0);
    }

    private static void printEightQueens(char[][] chessBoard, int[][] attackedPositions, int row) {
        if (row >= chessBoard.length) {
            printMatrix(chessBoard);
            return;
        }

        for (int column = 0; column < chessBoard[row].length; column++) {
            if (attackedPositions[row][column] == 0) {
                chessBoard[row][column] = '*';
                toggleAttackPositions(attackedPositions, 1, row, column);
                printEightQueens(chessBoard, attackedPositions, row + 1);
                toggleAttackPositions(attackedPositions, -1, row, column);
                chessBoard[row][column] = '-';

            }
        }
    }

    private static void toggleAttackPositions(int[][] field, int value, int queenRow, int queenColumn) {
        for (int row = 0; row < field.length; row++) {
            if (row != queenRow) {
                field[row][queenColumn] += value;
            }
        }

        int row = queenRow - 1;
        int column = queenColumn - 1;
        while (row >= 0 && column >= 0) {
            field[row][column] += value;
            row--;
            column--;
        }

        row = queenRow - 1;
        column = queenColumn + 1;
        while (row >= 0 && column < field[row].length) {
            field[row][column] += value;
            row--;
            column++;
        }
        
        row = queenRow + 1;
        column = queenColumn - 1;
        while (row < field.length && column >= 0) {
            field[row][column] += value;
            row++;
            column--;
        }
        
        row = queenRow + 1;
        column = queenColumn + 1;
        while (row < field.length && column < field[row].length) {
            field[row][column] += value;
            row++;
            column++;
        }
    }
    
    public static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                System.out.printf("%c ", matrix[row][column]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
