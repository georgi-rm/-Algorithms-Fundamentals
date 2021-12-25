import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        int[] combination = new int[k];

        combination(0, 1, n, combination);
    }

    private static void combination(int position, int startElement, int numberOfElements, int[] combination) {
        if (position >= combination.length) {
            for (int i = 0; i < combination.length; i++) {
                System.out.printf("%d ", combination[i]);
            }
            System.out.println();
            return;
        }

        for (int element = startElement; element <= numberOfElements; element++) {
            combination[position] = element;
            combination(position + 1, element, numberOfElements, combination);
        }
    }
}
