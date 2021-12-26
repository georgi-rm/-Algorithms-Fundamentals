import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static int numberOfElements;
    private static Integer[] combination;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        numberOfElements = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        combination = new Integer[k];

        combination(0, 1);
    }

    private static void combination(int position, int startElement) {
        if (position >= combination.length) {
            String output = Arrays.stream(combination)
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            System.out.println(output);
            return;
        }

        for (int element = startElement; element <= numberOfElements; element++) {
            combination[position] = element;
            combination(position + 1, element + 1);
        }
    }
}
