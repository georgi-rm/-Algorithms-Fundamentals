import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> graph = new HashMap<>();

        String input = scanner.nextLine();

        while (!input.equals("End")) {
            String[] nodes = input.split("-");

            graph.putIfAbsent(nodes[0], new ArrayList<>());
            graph.get(nodes[0]).add(nodes[1]);

            input = scanner.nextLine();
        }

        findCyclesInGraph(graph);
    }

    public static void findCyclesInGraph(Map<String, List<String>> graph) {

        Set<String> visited = new HashSet<>();
        Set<String> cycles = new HashSet<>();

        try {
            for (String node : graph.keySet()) {
                if (!visited.contains(node)) {
                    dfsFindCycles(node, visited, cycles, graph);
                }
            }
            System.out.println("Acyclic: Yes");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void dfsFindCycles(String node, Set<String> visited, Set<String> cycles, Map<String, List<String>> graph) {
        if (cycles.contains(node)) {
            throw new IllegalArgumentException("Acyclic: No");
        }

        if (visited.contains(node)) {
            return;
        }
        visited.add(node);
        cycles.add(node);

        if (graph.get(node) == null) {
            return;
        }

        for (String childNode : graph.get(node)) {
            dfsFindCycles(childNode, visited, cycles, graph);
        }
        cycles.remove(node);
    }
}
