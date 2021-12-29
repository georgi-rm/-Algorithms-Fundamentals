import java.util.*;

public class Main {

    private static final Map<Integer, Integer> indexMapper = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int numberOfVertices = Integer.parseInt(scanner.nextLine());
        int numberOfPairs = Integer.parseInt(scanner.nextLine());

        int[][] graph = new int[numberOfVertices][];

        for (int i = 0; i < graph.length; i++) {
            String[] edge = scanner.nextLine().split(":");

            indexMapper.put(Integer.parseInt(edge[0]), i);

            if (edge.length == 1) {
                graph[i] = new int[0];
            } else {
                graph[i] = Arrays.stream(edge[1].split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        }

        for (int i = 0; i < numberOfPairs; i++) {
            int[] pair = Arrays.stream(scanner.nextLine().split("-"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = pair[0];
            int destination = pair[1];

            int pathSize = findShortestPath(graph, indexMapper.get(source), indexMapper.get(destination));

            System.out.printf("{%d, %d} -> %d%n", source, destination, pathSize );
        }

    }

    private static int findShortestPath(int[][] graph, int source, int destination) {
        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);
        boolean[] visited = new boolean[graph.length + 1];
        int[] parent = new int[graph.length + 1];

        Arrays.fill(parent, -1);

        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == destination) {
                int pathSize = 0;
                while (parent[node] != -1) {
                    pathSize++;
                    node = parent[node];
                }
                return pathSize;
            }
            for (int i = 0; i < graph[node].length; i++) {
                int childNode = indexMapper.get(graph[node][i]);
                if (!visited[childNode]) {
                    visited[childNode] = true;
                    parent[childNode] = node;
                    queue.offer(childNode);
                }
            }
        }
        return -1;
    }
}
