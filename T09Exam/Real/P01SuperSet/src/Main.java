import java.util.Scanner;

public class Main {
    private static String[] elements;
    private static String[] currentCombination;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split(", ");

        for (int i = 0; i <= elements.length; i++) {
            currentCombination = new String[i];
            combinations(0, 0);
        }
    }


    private static void combinations(int index, int startIndex) {
        if (index >= currentCombination.length) {
            print(currentCombination);
            return;
        }

        for (int i = startIndex; i < elements.length; i++) {
            currentCombination[index] = elements[i];
            combinations(index + 1, i + 1);
        }
    }

    private static void print(String[] elements) {
        System.out.println(String.join(" ", elements));
    }
}
