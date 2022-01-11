import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfNodes = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int line = 0; line < numberOfNodes; line++) {
            String childNodes = scanner.nextLine();
            if (childNodes.length() == 0) {
                graph.add(new ArrayList<>());
            } else {
                graph.add(Arrays.stream(childNodes.split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()));
            }
        }

        for (int startNode = 0; startNode < graph.size() - 1; startNode++) {
            List<Integer> path = new ArrayList<>();
            boolean[] visited = new boolean[graph.size()];
            path.add(startNode);
            findAllPathsToLastNode(startNode, graph, visited, path);
        }
    }

    private static void findAllPathsToLastNode(int currentNode, List<List<Integer>> graph, boolean[] visited, List<Integer> path) {
        if (currentNode >= graph.size() - 1) {
            for (Integer pathNode : path) {
                System.out.printf("%d ", pathNode);
            }
            System.out.println();
            return;
        }

        if (visited[currentNode]) {
            return;
        }

        visited[currentNode] = true;
        for (Integer childNode : graph.get(currentNode)) {
            path.add(childNode);
            findAllPathsToLastNode(childNode, graph, visited, path);
            path.remove(path.size() - 1);
        }
        visited[currentNode] = false;
    }
}
