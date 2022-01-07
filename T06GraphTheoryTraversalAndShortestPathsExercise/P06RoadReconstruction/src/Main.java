import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<List<Integer>> graph = new ArrayList<>();
    private static final StringBuilder output = new StringBuilder();
    private static int time = 0;
    private static boolean[] visitedNodes;
    private static int[] disc;
    private static int[] low;
    private static int[] parentNode;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfBuildings = Integer.parseInt(scanner.nextLine());
        int numberOfRoads = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberOfBuildings; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < numberOfRoads; i++) {
            int[] edgeParts = Arrays.stream(scanner.nextLine().split(" - "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph.get(edgeParts[0]).add(edgeParts[1]);
            graph.get(edgeParts[1]).add(edgeParts[0]);
        }

        output.append("Important streets:").append(System.lineSeparator());

        visitedNodes = new boolean[graph.size()];

        disc = new int[graph.size()];
        low = new int[graph.size()];
        parentNode = new int[graph.size()];

        Arrays.fill(parentNode, -1);

        for (int node = 0; node < graph.size(); node++) {
            if (!visitedNodes[node]) {
                findImportantStreets(node);
            }
        }

        System.out.println(output.toString().trim());
    }

    private static void findImportantStreets(int node) {
        visitedNodes[node] = true;
        low[node] = ++time;
        disc[node] = low[node];

        for (Integer childNode : graph.get(node)) {
            if (!visitedNodes[childNode]) {
                parentNode[childNode] = node;
                findImportantStreets(childNode);

                low[node] = Math.min(low[node], low[childNode]);
                if (low[childNode] > disc[node]) {
                    output.append(node).append(" ").append(childNode).append(System.lineSeparator());
                }
            } else if (childNode != parentNode[node]){
                low[node] = Math.min(low[node], disc[childNode]);
            }
        }
    }
}
