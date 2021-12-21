import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfLines = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numberOfLines; i++) {
            String line = scanner.nextLine();
            if (line.length() == 0) {
                graph.add(new ArrayList<>());
            } else {
                List<Integer> connections = Arrays.stream(line.split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                graph.add(connections);
            }
        }

        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);

        for (Deque<Integer> connectedComponent : connectedComponents) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Connected component: ");
            while (!connectedComponent.isEmpty()) {
                stringBuilder.append(connectedComponent.poll()).append(" ");
            }
            System.out.println(stringBuilder.toString().trim());
        }

    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> connectedComponents = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                Deque<Integer> currentComponents = new ArrayDeque<>();
                dfs(i, graph, currentComponents, visited);
                connectedComponents.add(currentComponents);
            }
        }
        return connectedComponents;
    }

    private static void dfs(int currentNode, List<List<Integer>> graph, Deque<Integer> connectedComponents, boolean[] visited) {
        if (visited[currentNode]) {
            return;
        }

        visited[currentNode] = true;
        for (Integer nextNode : graph.get(currentNode)) {
            dfs(nextNode, graph, connectedComponents, visited);
        }
        connectedComponents.offer(currentNode);
    }


    public static Collection<String> topSort(Map<String, List<String>> graph) {
        Collection<String> topologicallySorted = new ArrayList<>();
        Map<String, Integer> predecessorCount = getPredecessorCount(graph);

        while (!graph.isEmpty()) {
            String nodeToRemove = graph.keySet()
                    .stream()
                    .filter(e -> predecessorCount.get(e) == 0)
                    .findFirst()
                    .orElse(null);

            if (nodeToRemove == null) {
                throw new IllegalArgumentException();
            }

            for (String childrenToRemove : graph.get(nodeToRemove)) {
                predecessorCount.put(childrenToRemove, predecessorCount.get(childrenToRemove) - 1);
            }

            topologicallySorted.add(nodeToRemove);
            graph.remove(nodeToRemove);
        }
        return topologicallySorted;
    }

    private static Map<String, Integer> getPredecessorCount(Map<String, List<String>> graph) {
        Map<String, Integer> predecessorCount = new HashMap<>();

        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            predecessorCount.putIfAbsent(node.getKey(), 0);
            for (String childNode : node.getValue()) {
                predecessorCount.putIfAbsent(childNode, 0);
                predecessorCount.put(childNode, predecessorCount.get(childNode) + 1);
            }
        }

        return predecessorCount;
    }
}
