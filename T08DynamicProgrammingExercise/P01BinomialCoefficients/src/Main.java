import java.util.Scanner;

public class Main {
    private static long[][] memoization;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        memoization = new long[n + 1][k + 1];

        long result = calculateCoefficient(n, k);
        System.out.println(result);
    }

    private static long calculateCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }

        if (memoization[n][k] != 0) {
            return memoization[n][k];
        }
        memoization[n][k] = calculateCoefficient(n - 1, k - 1) + calculateCoefficient(n - 1, k);
        return memoization[n][k];
    }
}
