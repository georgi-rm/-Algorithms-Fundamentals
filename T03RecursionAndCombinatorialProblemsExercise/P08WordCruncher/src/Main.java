import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] textParts = scanner.nextLine().split(", ");
        String targetText = scanner.nextLine();

        Map<String, Integer> textPartsOccurrences = new HashMap<>();
        for (String textPart : textParts) {
            textPartsOccurrences.putIfAbsent(textPart, 0);
            textPartsOccurrences.put(textPart, textPartsOccurrences.get(textPart) + 1);
        }

        List<String> outputParts = new ArrayList<>();

        findCombination(targetText, textPartsOccurrences, outputParts);
    }

    private static void findCombination(String targetText, Map<String, Integer> textPartsOccurrences, List<String> outputParts) {
        if (targetText == null || targetText.isEmpty()) {
            System.out.println(String.join(" ", outputParts));
            return;
        }
        for (String textPart : textPartsOccurrences.keySet()) {
            if (textPartsOccurrences.get(textPart) > 0) {
                if (targetText.startsWith(textPart)) {
                    textPartsOccurrences.put(textPart, textPartsOccurrences.get(textPart) - 1);
                    outputParts.add(textPart);
                    findCombination(targetText.substring(textPart.length()), textPartsOccurrences, outputParts);
                    outputParts.remove(outputParts.size() - 1);
                    textPartsOccurrences.put(textPart, textPartsOccurrences.get(textPart) + 1);
                }
            }
        }
    }
}
