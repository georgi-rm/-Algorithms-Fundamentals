import java.util.Scanner;

public class Main {
    private static String[] elements;
    private static String[] currentVariation;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
        int numberOfVariations = Integer.parseInt(scanner.nextLine());

        currentVariation = new String[numberOfVariations];

        variations(0);
    }


    private static void variations(int index) {
        if (index >= currentVariation.length) {
            print(currentVariation);
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            currentVariation[index] = elements[i];
            variations(index + 1);
        }
    }

    private static void swap(String[] elements, int firstIndex, int secondIndex) {
        String tempString = elements[firstIndex];
        elements[firstIndex] = elements[secondIndex];
        elements[secondIndex] = tempString;
    }

    private static void print(String[] elements) {
        System.out.println(String.join(" ", elements));
    }
}
