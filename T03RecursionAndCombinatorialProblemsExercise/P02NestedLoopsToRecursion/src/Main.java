import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int[] array;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfNestedLoops = Integer.parseInt(scanner.nextLine());

        array = new int[numberOfNestedLoops];

        nestedLoopsData(0);
    }

    private static void nestedLoopsData(int currentIndex) {
        if (currentIndex >= array.length) {
            printArray();
            return;
        }

        for (int i = 1; i <= array.length; i++) {
            array[currentIndex] = i;
            nestedLoopsData(currentIndex + 1);
        }
    }

    private static void printArray() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int number : array) {
            stringBuilder.append(number).append(" ");
        }
        System.out.println(stringBuilder.toString().trim());
    }
}
