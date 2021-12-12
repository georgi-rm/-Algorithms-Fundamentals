import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inputArray = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int numberToSearch = Integer.parseInt(scanner.nextLine());

        int foundOnIndex = findIndex(inputArray, numberToSearch);
        System.out.println(foundOnIndex);
    }

    private static int findIndex(int[] array, int key) {
        int start = 0;
        int end = array.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (key < array[mid]) {
                end = mid - 1;
            } else if (key > array[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
