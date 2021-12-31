import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<Integer, Long> cache;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        cache = new HashMap<>();

        long result = fibonacci(n);
        System.out.println(result);
    }

    private static long fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        long result = fibonacci(n - 1) + fibonacci(n - 2);

        cache.put(n, result);

        return result;
    }
}
