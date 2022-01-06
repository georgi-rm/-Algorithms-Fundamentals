import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final List<String> removedEdges = new ArrayList<>();
    private static final Map<String, Set<String>> graph = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        while (input != null && !input.equals("")) {
            String[] nodeParts = input.split(" -> ");
            String[] edgeDestinations = (nodeParts.length > 0) ? nodeParts[1].split("\\s+") : new String[0];
            Set<String> uniqueDestinations = new TreeSet<>();
            for (String nodeDestination : edgeDestinations) {
                if (!uniqueDestinations.add(nodeDestination)) {
                    if (nodeParts[0].compareTo(nodeDestination) > 0) {
                        removedEdges.add(String.format("%s - %s", nodeDestination, nodeParts[0]));
                    }
                }
            }

            graph.put(nodeParts[0], uniqueDestinations);
            input = reader.readLine();
        }

        while (true) {
            Set<String> visited = new HashSet<>();
            boolean isAcyclic = true;

            for (String node : graph.keySet()) {
                SortedSet<String> cycles = new TreeSet<>();
                if (dfsFindCycles(node, visited, cycles, null)) {
                    isAcyclic = false;
                    breakCycles(cycles);
                    break;
                }
            }

            if (isAcyclic) {
                break;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Edges to remove: ").append(removedEdges.size()).append(System.lineSeparator());
        Collections.sort(removedEdges);
        for (String removedEdge : removedEdges) {
            builder.append(removedEdge).append(System.lineSeparator());
        }
        System.out.println(builder.toString().trim());
    }

    private static void breakCycles(SortedSet<String> cycles) {
        for (String cycleNode : cycles) {
            for (String graphChildNode : graph.get(cycleNode)) {
                if (cycles.contains(graphChildNode)) {
                    for (String graphOtherChild : graph.get(cycleNode)) {
                        if (!graphChildNode.equals(graphOtherChild)
                                && cycles.contains(graphOtherChild)) {
                            removedEdges.add(String.format("%s - %s", cycleNode, graphChildNode));
                            graph.get(cycleNode).remove(graphChildNode);
                            graph.get(graphChildNode).remove(cycleNode);
                            return;
                        }
                    }
                }
            }
        }
        throw new IllegalStateException("breakCycles has no cycles to break");
    }


    private static boolean dfsFindCycles(String node, Set<String> visited, Set<String> cycles, String parent) {
        if (cycles.contains(node)) {
            return true;
        }

        if (visited.contains(node)) {
            return false;
        }
        visited.add(node);
        cycles.add(node);

        if (graph.get(node) == null) {
            return false;
        }

        for (String childNode : graph.get(node)) {
            if (!childNode.equals(parent)) {
                if (dfsFindCycles(childNode, visited, cycles, node)) {
                    return true;
                }
            }
        }
        cycles.remove(node);
        return false;
    }
}
