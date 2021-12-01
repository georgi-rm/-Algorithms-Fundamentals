import java.util.Scanner;

public class P02RecursiveDrawing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        drawFigure(n);
    }

    private static void drawFigure(int size) {
        if (size == 0) {
            return;
        }

        printSymbolMultipleTimes('*', size);
        System.out.println();

        drawFigure(size - 1);

        printSymbolMultipleTimes('#', size);
        System.out.println();
    }

    private static void printSymbolMultipleTimes(char symbol, int repeat) {
        if (repeat == 0) {
            return;
        }

        System.out.print(symbol);
        printSymbolMultipleTimes(symbol, repeat - 1);
    }
}
