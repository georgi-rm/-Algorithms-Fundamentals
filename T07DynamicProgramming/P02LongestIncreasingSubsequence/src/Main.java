import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] sequence = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] subSequenceLength = new int[sequence.length];
        int[] previousIndex = new int[sequence.length];

        Arrays.fill(previousIndex, -1);

        int longestSubsequenceLength = 0;
        int longestSubsequenceIndex = -1;

        for (int currentIndex = 0; currentIndex < sequence.length; currentIndex++) {
            int bestLength = 1;
            int bestIndex = -1;

            for (int i = currentIndex - 1; i >= 0; i--) {
                if (sequence[currentIndex] > sequence[i] && subSequenceLength[i] + 1 >= bestLength) {
                    bestLength = subSequenceLength[i] + 1;
                    bestIndex = i;
                }
            }

            previousIndex[currentIndex] = bestIndex;
            subSequenceLength[currentIndex] = bestLength;

            if (bestLength > longestSubsequenceLength) {
                longestSubsequenceLength = bestLength;
                longestSubsequenceIndex = currentIndex;
            }
        }

        List<Integer> longestLeftMostSubsequence = new ArrayList<>();

        int index = longestSubsequenceIndex;

        while (index >= 0) {
            longestLeftMostSubsequence.add(sequence[index]);
            index = previousIndex[index];
        }

        Collections.reverse(longestLeftMostSubsequence);

        String output = longestLeftMostSubsequence.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(output);
    }
}
