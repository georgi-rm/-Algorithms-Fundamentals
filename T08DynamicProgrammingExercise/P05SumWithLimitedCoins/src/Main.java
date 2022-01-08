import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] coins = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int targetSum = Integer.parseInt(scanner.nextLine());

        int numberOfCombinations = 0;

        Set<Integer> calculatedSums = new HashSet<>();
        calculatedSums.add(0);

        for (int coin : coins) {
            List<Integer> tempCalculatedSums = new ArrayList<>(calculatedSums);
            for (Integer calculatedSum : tempCalculatedSums) {
                int newSum = calculatedSum + coin;
                if (newSum == targetSum) {
                    numberOfCombinations++;
                }

                calculatedSums.add(newSum);
            }
        }

        System.out.println(numberOfCombinations);
    }
}
