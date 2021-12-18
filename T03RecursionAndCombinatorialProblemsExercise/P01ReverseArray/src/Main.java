import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] array = scanner.nextLine().split("\\s+");

        reverseArray(array, array.length - 1);
    }

    private static void reverseArray(String[] array, int index) {
        if (index < 0) {
            return;
        }

        System.out.printf("%s ", array[index]);
        reverseArray(array, index - 1);
    }
}
