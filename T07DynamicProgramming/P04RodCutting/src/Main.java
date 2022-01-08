import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int[] bestPrices;
    private static int[] prices;
    private static int[] prevCut;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        prices = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int length = Integer.parseInt(scanner.nextLine());

        bestPrices = new int[prices.length];
        prevCut = new int[prices.length];

        int maxPrice = cutRope(length);
        System.out.println(maxPrice);
        printCuts(length);
    }

    private static int cutRope(int length) {
        if (length == 0) {
            return 0;
        }

        if (bestPrices[length] != 0) {
            return bestPrices[length];
        }

        int currentBest = bestPrices[length];
        for (int cutPart = 1; cutPart <= length; cutPart++) {
            currentBest = Math.max(currentBest, prices[cutPart] + cutRope(length - cutPart));
            if (currentBest > bestPrices[length]) {
                bestPrices[length] = currentBest;
                prevCut[length] = cutPart;
            }
        }

        return bestPrices[length];
    }

    private static void printCuts(int length) {
        while (length - prevCut[length] != 0) {
            System.out.print(prevCut[length] + " ");
            length -= prevCut[length];
        }
        System.out.println(prevCut[length]);
    }
}
