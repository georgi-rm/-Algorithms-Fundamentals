import java.util.*;

public class Main {
    private static final Map<Character, Integer> areas = new TreeMap<>();
    private static char[][] field;
    private static boolean[][] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfRows = Integer.parseInt(scanner.nextLine());
        field = new char[numberOfRows][];
        visited = new boolean[numberOfRows][];

        for (int row = 0; row < numberOfRows; row++) {
            field[row] = scanner.nextLine().toCharArray();
            visited[row] = new boolean[field[row].length];
        }

        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[row].length; column++) {
                if (!visited[row][column]) {
                    char areaSymbol = field[row][column];
                    dfs(row, column, areaSymbol);
                    areas.putIfAbsent(areaSymbol, 0);
                    areas.put(areaSymbol, areas.get(areaSymbol) + 1);
                }
            }
        }


        int numberOfAreas = areas.values().stream()
                .mapToInt(e->e)
                .sum();

        StringBuilder output = new StringBuilder();
        output.append("Areas: ").append(numberOfAreas).append(System.lineSeparator());

        for (Map.Entry<Character, Integer> area : areas.entrySet()) {
            output.append("Letter '").append(area.getKey()).append("' -> ")
                    .append(area.getValue()).append(System.lineSeparator());
        }
        System.out.println(output.toString().trim());
    }

    private static void dfs(int row, int column, char areaSymbol) {
        if (isNotInBounds(row, column, field)) {
            return;
        }

        if (visited[row][column]) {
            return;
        }

        if (field[row][column] != areaSymbol) {
            return;
        }

        visited[row][column] = true;
        dfs(row + 1, column, areaSymbol);
        dfs(row - 1, column, areaSymbol);
        dfs(row, column + 1, areaSymbol);
        dfs(row, column - 1, areaSymbol);
    }

    private static boolean isNotInBounds(int row, int column, char[][] field) {
        return row < 0 || row >= field.length || column < 0 || column >= field[row].length;
    }
}
