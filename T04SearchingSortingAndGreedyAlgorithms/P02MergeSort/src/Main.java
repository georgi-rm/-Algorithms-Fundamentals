import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        sort(array);

        for (int number : array) {
            System.out.printf("%d ", number);
        }
    }

    private static void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int begin, int end) {
        if (begin >= end) {
            return;
        }

        int halfIndex = (begin + end) / 2;

        mergeSort(array, begin, halfIndex);
        mergeSort(array, halfIndex + 1, end);

        merge(array, begin, halfIndex, end);
    }

    private static void merge(int[] array, int begin, int mid, int end) {
        if (mid < 0 || mid >= array.length || array[mid] < array[mid + 1]) {
            return;
        }

        int[] originalData = new int[array.length];

        for (int i = begin; i <= end; i++) {
            originalData[i] = array[i];
        }

        int leftIndex = begin;
        int rightIndex = mid + 1;

        for (int i = begin; i <= end; i++) {
            if (leftIndex > mid) {
                array[i] = originalData[rightIndex++];
            } else if (rightIndex > end) {
                array[i] = originalData[leftIndex++];
            } else if (originalData[leftIndex] <= originalData[rightIndex]) {
                array[i] = originalData[leftIndex++];
            } else if (originalData[leftIndex] > originalData[rightIndex]) {
                array[i] = originalData[rightIndex++];
            }
        }
    }
}
