import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = reader.readLine().substring(10).split(", ");
        int[] universe = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            universe[i] = Integer.parseInt(elements[i]);
        }
        int numberOfSets = Integer.parseInt(reader.readLine().substring(16));
        List<int[]> sets = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {
            String[] setElements = reader.readLine().split(", ");
            int[] set = new int[setElements.length];
            for (int j = 0; j < setElements.length; j++) {
                set[j] = Integer.parseInt(setElements[j]);
            }
            sets.add(set);
        }
        List<int[]> chosenSets = chooseSets(sets, universe);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Sets to take (%d):%n", chosenSets.size()));
        for (int[] set : chosenSets) {
            sb.append("{ ");
            sb.append(Arrays.toString(set).replaceAll("\\[|]", ""));
            sb.append(" }").append(System.lineSeparator());
        }
        System.out.println(sb);
    }

    public static List<int[]> chooseSets(List<int[]> sets, int[] universe) {

        List<int[]> chosenSets = new ArrayList<>();

        Set<Integer> setOfUniverse = Arrays.stream(universe)
                .boxed()
                .collect(Collectors.toSet());

        while (!setOfUniverse.isEmpty()) {
            int maxContainedElements = 0;
            int[] chosenSet = sets.get(0);

            for (int[] set : sets) {
                int count = 0;

                for (int element : set) {
                    if (setOfUniverse.contains(element)) {
                        count++;
                    }
                }

                if (count > maxContainedElements) {
                    maxContainedElements = count;
                    chosenSet = set;
                }
            }

            chosenSets.add(chosenSet);

            for (int element : chosenSet) {
                setOfUniverse.remove(element);
            }
        }

        return chosenSets;
    }
}
