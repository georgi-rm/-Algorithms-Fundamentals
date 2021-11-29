import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] intArray = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(sum(intArray, 0));

    }

    public static int sum(int[] array, int index) {
        if (index >= array.length) {
            return 0;
        }

        return array[index] + sum(array, index + 1);
    }
}
