import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] coins = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int sum = Integer.parseInt(scanner.nextLine());

        int[] combinations = new int[sum + 1];

        combinations[0] = 1;

        for (int coin : coins) {
            for (int j = coin; j <= sum; j++) {
                combinations[j] += combinations[j - coin];
            }
        }

        System.out.println(combinations[sum]);
    }
}
