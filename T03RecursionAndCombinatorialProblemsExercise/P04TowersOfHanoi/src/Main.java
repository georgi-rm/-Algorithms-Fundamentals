import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static Deque<Integer> source;
    private static Deque<Integer> spare;
    private static Deque<Integer> destination;
    private static int steps;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfDisks = Integer.parseInt(scanner.nextLine());

        steps = 1;
        source = new ArrayDeque<>();
        spare = new ArrayDeque<>();
        destination = new ArrayDeque<>();

        for (int i = numberOfDisks; i > 0; i--) {
            source.push(i);
        }

        printRodsState(false);

        solve(numberOfDisks, source, destination, spare);
    }

    private static void solve(int disk, Deque<Integer> source, Deque<Integer> destination, Deque<Integer> spare) {
        if (disk == 1) {
            destination.push(source.pop());
            printRodsState(true);
            steps++;
            return;
        }

        solve(disk - 1, source, spare, destination);
        solve(1, source, destination, spare);
        solve(disk - 1, spare, destination, source);
    }

    private static void printRodsState(boolean printSteps) {
        StringBuilder builder = new StringBuilder();
        if (printSteps) {
            builder.append("Step #").append(steps).append(": Moved disk").append(System.lineSeparator());
        }
        builder.append("Source: ").append(getRodElements(source)).append(System.lineSeparator())
                .append("Destination: ").append(getRodElements(destination)).append(System.lineSeparator())
                .append("Spare: ").append(getRodElements(spare)).append(System.lineSeparator());

        System.out.println(builder);
    }

    private static String getRodElements(Deque<Integer> rod) {
        return rod.stream()
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }
}
