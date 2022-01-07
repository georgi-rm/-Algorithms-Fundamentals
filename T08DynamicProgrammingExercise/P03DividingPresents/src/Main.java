import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int[] presents = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int totalSum = Arrays.stream(presents).sum();

        int[] sums = new int[totalSum + 1];

        Arrays.fill(sums, -1);
        sums[0] = 0;

        for (int i = 0; i < presents.length; i++) {
            for (int prevSumIndex = totalSum - presents[i]; prevSumIndex >= 0; prevSumIndex--) {
                int presentValue = presents[i];

                if (sums[prevSumIndex] != -1 && sums[prevSumIndex + presentValue] == -1) {
                    sums[prevSumIndex + presentValue] = i;
                }
            }
        }

        int half = totalSum / 2;

        for (int i = half; i >= 0; i--) {
            if (sums[i] == -1) {
                continue;
            }

            System.out.printf("Difference: %d%n", totalSum - i - i);
            System.out.printf("Alan:%d Bob:%d%n", i, totalSum - i);
            System.out.print("Alan takes:");

            while (i != 0) {
                System.out.printf(" %d", presents[sums[i]]);
                i -= presents[sums[i]];
            }

            System.out.println();
            System.out.println("Bob takes the rest.");
        }
    }
}
