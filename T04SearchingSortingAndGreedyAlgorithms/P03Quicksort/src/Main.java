import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] elements = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        quickSort(elements, 0, elements.length - 1);

        String output = Arrays.stream(elements)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(output);
    }

    private static void quickSort(int[] elements, int left, int right) {
        if (left >= right) {
            return;
        }

        int index = partition(elements, left, right);
        quickSort(elements, left, index - 1);
        quickSort(elements, index + 1, right);
    }

    private static int partition(int[] elements, int left, int right) {
        int pivot = elements[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (elements[j] <= pivot) {
                i++;
                swap(elements, i, j);
            }
        }

        swap(elements, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] elements, int first, int second) {
        int temp = elements[first];
        elements[first] = elements[second];
        elements[second] = temp;
    }
}
