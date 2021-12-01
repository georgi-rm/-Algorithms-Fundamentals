import java.util.Scanner;

public class P03GeneratingBinaryVectors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int bits = Integer.parseInt(scanner.nextLine());

        int[] vector = new int[bits];

        generateVector(vector, 0);
    }

    private static void generateVector(int[] vector, int index) {
        if (index >= vector.length) {
            for (int i = 0; i < vector.length; i++) {
                System.out.print(vector[i]);
            }
            System.out.println();
            return;
        }

        for (int i = 0; i <= 1; i++) {
            vector[index] = i;
            generateVector(vector, index + 1);
        }
    }
}
