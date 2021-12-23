import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfNodes = Integer.parseInt(scanner.nextLine());
        int numberOfEdges = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numberOfNodes + 1; i++) {
            graph.add(i, new ArrayList<>());
        }

        for (int i = 0; i < numberOfEdges; i++) {
            Integer[] edgeData = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            Integer source = edgeData[0];
            Integer destination = edgeData[1];

            graph.get(source).add(destination);
        }

        int startNode = Integer.parseInt(scanner.nextLine());
        int endNode = Integer.parseInt(scanner.nextLine());

        boolean[] visited = new boolean[numberOfNodes + 1];
        Integer[] parentNodes = new Integer[numberOfNodes + 1];

        bfsShortestPath(graph, visited, parentNodes, startNode, endNode);

        List<Integer> path = new ArrayList<>();

        Integer prevNode = endNode;

        while (prevNode != null){
            path.add(prevNode);
            if (prevNode == startNode) {
                break;
            }
            prevNode = parentNodes[prevNode];
        }

        System.out.printf("Shortest path length is: %d%n", path.size() - 1);

        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.printf("%d ", path.get(i));
        }
        System.out.println();
    }

    private static void bfsShortestPath(List<List<Integer>> graph, boolean[] visited, Integer[] parent, int startNode, int endNode) {
        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(startNode);
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == endNode) {
                return;
            }

            for (Integer childNode : graph.get(node)) {
                if (!visited[childNode]) {
                    visited[childNode] = true;
                    parent[childNode] = node;
                    queue.offer(childNode);
                }
            }
        }
    }
}
