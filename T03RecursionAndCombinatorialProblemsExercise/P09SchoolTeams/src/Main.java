import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String[] girlNames;
    private static String[] boyNames;
    private static List<String> girlsCombinations = new ArrayList<>();
    private static List<String> boysCombinations = new ArrayList<>();
    private static String[] combinationGirls = new String[3];
    private static String[] combinationBoys = new String[2];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        girlNames = scanner.nextLine().split(", ");
        boyNames = scanner.nextLine().split(", ");

        combineGirls(0, 0);
        combineBoys(0, 0);

        StringBuilder builder = new StringBuilder();
        for (String girlsCombination : girlsCombinations) {
            for (String boysCombination : boysCombinations) {
                builder.append(girlsCombination).append(", ")
                        .append(boysCombination).append(System.lineSeparator());
            }
        }
        System.out.println(builder.toString().trim());
    }

    private static void combineGirls(int position, int startIndex) {
        if (position >= combinationGirls.length) {
            girlsCombinations.add(String.join(", ", combinationGirls));
            return;
        }

        for (int i = startIndex; i < girlNames.length; i++) {
            combinationGirls[position] = girlNames[i];
            combineGirls(position + 1, i + 1);
        }
    }

    private static void combineBoys(int position, int startIndex) {
        if (position >= combinationBoys.length) {
            boysCombinations.add(String.join(", ", combinationBoys));
            return;
        }

        for (int i = startIndex; i < boyNames.length; i++) {
            combinationBoys[position] = boyNames[i];
            combineBoys(position + 1, i + 1);
        }
    }
}
