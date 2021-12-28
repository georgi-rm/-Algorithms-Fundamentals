import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().substring(5));


        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);

        for (Map.Entry<Integer, Integer> usedCoin : usedCoins.entrySet()) {
            System.out.println(usedCoin.getKey() + " -> " + usedCoin.getValue());
        }
    }

    public static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
       int currentSum = targetSum;
       Map<Integer, Integer> usedCoins = new LinkedHashMap<>();
       List<Integer> sortedCoins = Arrays.stream(coins)
               .boxed()
               .sorted(Comparator.reverseOrder())
               .collect(Collectors.toList());

        for (Integer coin : sortedCoins) {
            int numberOfCoins = currentSum / coin;
            if( numberOfCoins != 0) {
                usedCoins.put(coin, numberOfCoins);
                currentSum %= coin;
            }
        }

        if (currentSum > 0) {
            throw new IllegalArgumentException("Error");
        }

        return usedCoins;
    }
}