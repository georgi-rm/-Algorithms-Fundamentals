import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        int result = pascalPyramid(n, k);
        System.out.println(result);
    }

    private static int pascalPyramid(int n, int k) {
        if (k > n) {
            return 0;
        }

        if (k == 0 || n == k) {
            return 1;
        }

        return pascalPyramid(n - 1, k) + pascalPyramid(n - 1, k - 1);
    }
}
