import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static long[] salaries;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberOfEmployees = Integer.parseInt(reader.readLine());

        visited = new boolean[numberOfEmployees];
        salaries = new long[numberOfEmployees];

        int[] numberOfManagers = new int[numberOfEmployees];

        for (int i = 0; i < numberOfEmployees; i++) {
            graph.add(new ArrayList<>());

            String line = reader.readLine();

            for (int ixEmployee = 0; ixEmployee < line.length(); ixEmployee++) {
                if (line.charAt(ixEmployee) == 'Y') {
                    numberOfManagers[ixEmployee]++;
                    graph.get(i).add(ixEmployee);
                }
            }
        }

        List<Integer> bosses = new ArrayList<>();

        for (int employee = 0; employee < numberOfManagers.length; employee++) {
            if (numberOfManagers[employee] == 0) {
                bosses.add(employee);
            }
        }

        for (Integer boss : bosses) {
            dfs(boss);
        }

        long sum = Arrays.stream(salaries).sum();
        System.out.println(sum);
    }

    private static void dfs(int employee) {
        if (visited[employee]) {
            return;
        }

        visited[employee] = true;

        if (graph.get(employee).isEmpty()) {
            salaries[employee] = 1;
            return;
        }

        long salary = 0;
        for (Integer worker : graph.get(employee)) {
            dfs(worker);
            salary += salaries[worker];
        }
        salaries[employee] = salary;
    }
}
