import java.util.Scanner;

public class P04RecursiveFactorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long number = Integer.parseInt(scanner.nextLine());

        System.out.println(calculateFactorial(number));
    }

    private static long calculateFactorial(long number) {
        if (number == 1) {
            return 1;
        }

        return number * calculateFactorial(number - 1);
    }
}
