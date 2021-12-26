import java.util.Scanner;

public class Main {
    private static int numberOfElements;
    private static int[] combination;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        numberOfElements = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        combination = new int[k];

        combination(0, 1);
    }

    private static void combination(int position, int startElement) {
        if (position >= combination.length) {
            for (int i = 0; i < combination.length; i++) {
                System.out.printf("%d ", combination[i]);
            }
            System.out.println();
            return;
        }

        for (int element = startElement; element <= numberOfElements; element++) {
            combination[position] = element;
            combination(position + 1, element);
        }
    }
}
