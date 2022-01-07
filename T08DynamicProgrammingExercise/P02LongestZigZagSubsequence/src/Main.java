import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final int GRATER = 0;
    private static final int LOWER = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] initialSequence = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] lengthOfSubSequence = new int[initialSequence.length][2];

        lengthOfSubSequence[0][0] = 1;
        lengthOfSubSequence[0][1] = 1;

        int[][] previousIndex = new int[initialSequence.length][2];

        for (int[] line : previousIndex) {
            Arrays.fill(line, -1);
        }

        int longestSubsequenceLength = 0;
        int longestSubsequenceIndex = -1;
        int longestSubsequenceType = -1;

        for (int currentIndex = 0; currentIndex < initialSequence.length; currentIndex++) {

            for (int prevIndex = currentIndex - 1; prevIndex >= 0; prevIndex--) {
                if (initialSequence[currentIndex] > initialSequence[prevIndex] && lengthOfSubSequence[prevIndex][LOWER] + 1 >= lengthOfSubSequence[currentIndex][GRATER]) {
                    lengthOfSubSequence[currentIndex][GRATER] = lengthOfSubSequence[prevIndex][LOWER] + 1;
                    previousIndex[currentIndex][GRATER] = prevIndex;
                }

                if (initialSequence[currentIndex] < initialSequence[prevIndex] && lengthOfSubSequence[prevIndex][GRATER] + 1 >= lengthOfSubSequence[currentIndex][LOWER]) {
                    lengthOfSubSequence[currentIndex][LOWER] = lengthOfSubSequence[prevIndex][GRATER] + 1;
                    previousIndex[currentIndex][LOWER] = prevIndex;
                }
            }

            if (lengthOfSubSequence[currentIndex][GRATER] > longestSubsequenceLength) {
                longestSubsequenceLength = lengthOfSubSequence[currentIndex][GRATER];
                longestSubsequenceIndex = currentIndex;
                longestSubsequenceType = GRATER;
            }

            if (lengthOfSubSequence[currentIndex][LOWER] > longestSubsequenceLength) {
                longestSubsequenceLength = lengthOfSubSequence[currentIndex][LOWER];
                longestSubsequenceIndex = currentIndex;
                longestSubsequenceType = LOWER;
            }


        }

        List<Integer> longestLeftMostSubsequence = new ArrayList<>();

        int index = longestSubsequenceIndex;

        while (index >= 0) {
            longestLeftMostSubsequence.add(initialSequence[index]);
            index = previousIndex[index][longestSubsequenceType];
            longestSubsequenceType = ( longestSubsequenceType == GRATER) ? LOWER : GRATER;
        }

        Collections.reverse(longestLeftMostSubsequence);

        String output = longestLeftMostSubsequence.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(output);
    }
}
